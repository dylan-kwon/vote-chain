// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.26;

import "./Model.sol";
import "./oracle/ChainLinkClient.sol";

import "@openzeppelin/contracts/utils/Strings.sol";
import {ConfirmedOwner} from "@chainlink/contracts/src/v0.8/shared/access/ConfirmedOwner.sol";

contract Vote is ChainLinkClient {
    
    string public dbName;

    uint public voteCount = 0;

    mapping (uint => Model.Vote) public votes;

    uint public latestVoteId;

    enum SourceType {
        CreateVote,
        CloseVote
    }

    mapping (SourceType => string) private sources;

    constructor(
        string memory _dbName,
        address router, 
        bytes32 donId, 
        bytes memory secretsUrls,
        uint64 subscriptionId,
        uint32 chainLinkGasLimit,
        string memory createVoteSource,
        string memory closeVoteSource
    ) ChainLinkClient(
        router, 
        donId,
        secretsUrls,
        subscriptionId,
        chainLinkGasLimit
    ) {
        setDbName(_dbName);
        setCreateVoteSource(createVoteSource);
        setCloseVoteSource(closeVoteSource);
    }

    modifier onlyVoteOwner(uint id) {
        require(
            msg.sender == votes[id].owner,
            "Only the vote owner can use it."
        );
        _;
    }

    modifier isVoteOpen(uint id) {
        Model.Vote storage vote = votes[id];
        require(
            !vote.isClosed,
            "This vote is already closed."
        );
        _;
    }

    modifier hasNotVoted(uint id) {
        Model.Vote storage vote = votes[id];
        Model.Voter memory voter = vote.voters[msg.sender];
        require(
            voter.votings.length == 0,
            "This voter has already voted."
        );
        _;
    }

    event CreateVote(
        address indexed owner,
        uint indexed voteId,
        bytes32 indexed chainLinkRequestId
    );

    event Voting(
        address indexed owner,
        uint indexed voteId,
        address indexed voter,
        uint[] indexes
    );

    event CloseVote(
        address indexed owner,
        uint indexed voteId,
        bytes32 indexed chainLinkRequestId
    );

    function setDbName(string memory _dbName) public {
        dbName = _dbName;
    }

    function createVote(
        string calldata title,
        string calldata content,
        string calldata imageUrl,
        bool isAllowDuplicateVoting,
        string[] calldata ballotItems
    ) external {
        require(
            ballotItems.length > 0, 
            "Must be at least one Ballot Item."
        );

        uint newId = ++voteCount;
        Model.Vote storage newVote = votes[newId];

        newVote.id = newId;
        newVote.owner = msg.sender;
        newVote.title = title;
        newVote.content = content;
        newVote.imageUrl = imageUrl;
        newVote.createdAt  = block.timestamp;
        newVote.isAllowDuplicateVoting = isAllowDuplicateVoting;
        newVote.isClosed = false;
        
        for (uint i = 0; i < ballotItems.length; i++) {
            Model.BallotItem memory item = Model.BallotItem(
                ballotItems[i], 0
            );
            newVote.ballotItems.push(item);
        }

        latestVoteId = newId;

        emit CreateVote(
            msg.sender,
            newVote.id,
            requestCreateVote(newVote)
        );
    }

    function requestCreateVote(Model.Vote storage vote) internal returns (bytes32) {
        string memory source = sources[SourceType.CreateVote];
        string[] memory args = new string[](7);
        
        args[0] = dbName;
        args[1] = Strings.toString(vote.id);
        args[2] = vote.title;
        args[3] = vote.content;
        args[4] = vote.imageUrl;
        args[5] = Strings.toString(vote.createdAt);
        args[6] = vote.isClosed ? "true" : "false";

        return request(source, args);
    }

     function getVoteBallotItems(uint id) external view returns (Model.BallotItem[] memory) {
        return votes[id].ballotItems;
    }

    function voting(
        uint id, 
        uint[] calldata indexes
    ) external isVoteOpen(id) hasNotVoted(id) {
        require(
            indexes.length > 0, 
            "Must vote for at least one option."
        );

        Model.Vote storage vote = votes[id];

        if(indexes.length > 1) {
            require(
                vote.isAllowDuplicateVoting, 
                "This vote does not allow duplicate voting."
            );
        }

        for (uint i = 0; i < indexes.length; i++) {
            uint index = indexes[i];
            require (
                index < vote.ballotItems.length,
                "This is an invalid index."
            );
            vote.ballotItems[index].count++;
        }

        vote.voters[msg.sender] = Model.Voter(
            msg.sender, indexes
        );

        emit Voting(
            msg.sender,
            id,
            msg.sender,
            indexes
        );
    }

    function closeVote(uint id) external onlyVoteOwner(id) isVoteOpen(id)  {
        Model.Vote storage vote = votes[id];
        vote.isClosed = true;

        emit CloseVote(
            msg.sender,
            id,
            requestCloseVote(vote)
        );
    }

    function requestCloseVote(Model.Vote storage vote) internal returns (bytes32) {
        string memory source = sources[SourceType.CloseVote];
        string[] memory args = new string[](2);

        args[0] = dbName;
        args[1] = Strings.toString(vote.id);
        
        return request(source, args);
    }

    function setCreateVoteSource(string memory source) public onlyOwner {
        sources[SourceType.CreateVote] = source;
    }

    function setCloseVoteSource(string memory source) public onlyOwner {
        sources[SourceType.CloseVote] = source;
    }
}
    
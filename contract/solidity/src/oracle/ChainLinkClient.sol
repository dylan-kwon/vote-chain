// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.26;

import {FunctionsClient} from "@chainlink/contracts/src/v0.8/functions/v1_0_0/FunctionsClient.sol";
import {FunctionsRequest} from "@chainlink/contracts/src/v0.8/functions/v1_0_0/libraries/FunctionsRequest.sol";

contract ChainLinkClient is FunctionsClient {
    using FunctionsRequest for FunctionsRequest.Request;

    address public owner;

    address public router;
    bytes32 public donId;
    
    bytes public secretsUrls;
    
    uint64 public subscriptionId;

    uint32 public gasLimit;

    bytes32 public lastRequestId;

    constructor(
        address _router, 
        bytes32 _donId,
        bytes memory _secretsUrls, 
        uint64 _subscriptionId,
        uint32 _gasLimit
    ) FunctionsClient(_router) {
        owner = msg.sender;
        setNetwork(_router, _donId);
        setSecretsUrls(_secretsUrls);
        setSubscriptionId(_subscriptionId);
        setGasLimit(_gasLimit);
    }

    modifier onlyOwner() {
        require(
            msg.sender == owner,
            "Only the contract owner can use it."
        );
        _;
    } 

    event ChainLinkResponse(
        bytes32 indexed requestId,
        string response,
        string err
    );

    function setNetwork(address _router, bytes32 _donId) public onlyOwner {
        router = _router;
        donId = _donId;
    }

    function setSecretsUrls(bytes memory _secretsUrls) public onlyOwner {
        secretsUrls = _secretsUrls;
    }

    function setSubscriptionId(uint64 _subscriptionId) public onlyOwner{
        subscriptionId = _subscriptionId;
    }
    
    function setGasLimit(uint32 _gasLimit) public onlyOwner{
        gasLimit = _gasLimit;
    }

    function request(
        string memory source,
        string[] memory args
    ) internal returns (bytes32) {
        FunctionsRequest.Request memory req;
        req.initializeRequestForInlineJavaScript(source);

        if (secretsUrls.length > 0) {
            req.addSecretsReference(secretsUrls);
        }

        if (args.length > 0) {
            req.setArgs(args);
        }

        lastRequestId = _sendRequest(
            req.encodeCBOR(),
            subscriptionId,
            gasLimit,
            donId
        );

        return lastRequestId;
    }

    function fulfillRequest(
        bytes32 requestId,
        bytes memory response,
        bytes memory err
    ) internal override {
        require(
            lastRequestId == requestId,
            "Unexpected Request ID"
        );
        
        emit ChainLinkResponse(
            requestId,
            string(response),
            string(err)
        );
    }
}
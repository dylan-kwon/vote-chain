// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.26;

library Model {
    struct Vote {
        uint id;
        address owner;
        string title;
        string content;
        string imageUrl;
        BallotItem[] ballotItems;
        mapping(address => Voter) voters;
        uint voterCount;
        bool isAllowDuplicateVoting;
        uint createdAt;
        bool isClosed;
    }

    struct BallotItem  {
        string name;
        uint count;
    }

    struct Voter {
        address id;
        uint[] votings;
    }
}
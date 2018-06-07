package oeg.upm.isantana.ldvserver.algorithm.utils;

public enum AlgorithmOutCodes {
	
	EVERYTHING_OK, //so far so good
	ROOT_NODE_PENDING, //user has not requested a Root node
	ROOT_NODE_NOT_FOUND, // user has requested a Root node, but we can't find it
	MAX_NODES_EXCEED, // too many nodes
	TIMEOUT //too much time executing
}

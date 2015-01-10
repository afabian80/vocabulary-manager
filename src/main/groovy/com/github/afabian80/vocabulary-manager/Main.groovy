package com.github.afabian80.vocabulary_manager

class Main {
	static void main(String[] args) {
		if(args.length != 2) {
			println "Error: input files not defined"
			println "Usage: ..."
			System.exit(1)
		}
		args.each { println it }
	}
}
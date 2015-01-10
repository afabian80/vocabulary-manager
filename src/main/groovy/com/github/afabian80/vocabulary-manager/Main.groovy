package com.github.afabian80.vocabulary_manager

class Main {
	static void main(String[] args) {
		if(args.length != 2) {
			println "Error: input files not defined"
			println "Usage: ..."
			System.exit(1)
		}
		Main main = new Main()
		main.run(args[0], args[1])
	}

	void run(file1, file2) {
		println "Called with $file1, $file2"
		SubtitleProcessor sp = new SubtitleProcessor()
		sp.text = new File(file1).text
	}
}
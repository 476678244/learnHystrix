package learnHystrix.regex;

public class RegexTest {

	public static void main(String[] args) {
		String s = "{\"FileStatuses\":{\"FileStatus\":[{\"pathSuffix\":\"Cindy\",\"type\":\"DIRECTORY\",\"length\":0,\"owner\":\"mercury\",\"group\":\"mercury\",\"permission\":\"755\",\"accessTime\":0,\"modificationTime\":1481624951801,\"blockSize\":0,\"replication\":0},{\"pathSuffix\":\"_SUCCESS\",\"type\":\"FILE\",\"length\":0,\"owner\":\"mercury\",\"group\":\"mercury\",\"permission\":\"644\",\"accessTime\":1481251386670,\"modificationTime\":1481251386672,\"blockSize\":134217728,\"replication\":3},{\"pathSuffix\":\"part-m-00000\",\"type\":\"FILE\",\"length\":333913966,\"owner\":\"mercury\",\"group\":\"mercury\",\"permission\":\"644\",\"accessTime\":1482911871749,\"modificationTime\":1481251386544,\"blockSize\":134217728,\"replication\":3}]}}\n";
		System.out.println(s.contains("\"type\":\"DIRECTORY\""));
		System.out.println(s.matches("DIRECTORY"));
		int a = 0;
	}

}

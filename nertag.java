package nlp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.fnlp.nlp.cn.tag.NERTagger;

public class nertag {
	public static void main(String[] args) throws Exception {
		// 用做完词性标注的数据,进行命名实体识别
		NERTagger tag = new NERTagger("/Users/zoe/Documents/nlp/models/seg.m",
				"/Users/zoe/Documents/nlp/models/pos.m");

		OutputStreamWriter writer = null;
		BufferedWriter bufferedWriter = null;
		String encoding = "UTF-8";
		File refile = new File("/Users/zoe/Documents/data/data_nertag.txt");
		if (!refile.exists())
			refile.createNewFile();

		writer = new OutputStreamWriter(new FileOutputStream(refile), encoding);// 确认流的输出文件和编码格式
		bufferedWriter = new BufferedWriter(writer);
		
		String result="";
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap = tag.tagFile("/Users/zoe/Documents/data/data_postag.txt");

		Map<String, String> map = hashmap;
		for (java.util.Map.Entry<String, String> entry : map.entrySet()) {
			result+="{"+entry.getKey()+":"+entry.getValue()+"}\r\n";
		}
		bufferedWriter.write(result);
		bufferedWriter.flush(); // 刷新缓冲区的数据到文件
		bufferedWriter.close();
		writer.close();

	}
}


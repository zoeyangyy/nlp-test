package nlp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.fnlp.nlp.cn.CNFactory;
import org.fnlp.nlp.corpus.StopWords;
import org.fnlp.util.exception.LoadModelException;

public class stopwords {

	public static void main(String[] args) throws IOException, LoadModelException {
		// 创建中文处理工厂对象，并使用“models”目录下的模型文件初始化
	    CNFactory factory = null;
	    
	    factory = CNFactory.getInstance("models");
	    
	    InputStreamReader reader = null;
	    BufferedReader bufferedReader =null;
	    OutputStreamWriter writer =null;
	    BufferedWriter bufferedWriter = null;
	    
	    try {
            String encoding="UTF-8";
            File file=new File("/Users/zoe/Documents/data/data2.txt");
            File refile=new File("/Users/zoe/Documents/data/data_stopwords.txt");
            if(!refile.exists()) refile.createNewFile();
            if(file.isFile() && file.exists()){ //判断文件是否存在
            	String lineTxt = null;
            	
                reader = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                bufferedReader = new BufferedReader(reader);
               
                writer = new OutputStreamWriter(new FileOutputStream(refile),encoding);//确认流的输出文件和编码格式
                bufferedWriter = new BufferedWriter(writer);
                
                StopWords sw = new StopWords();
        		sw.read("/Users/zoe/Documents/nlp/models/stopwords/StopWords.txt");
        		
                int i=0;
                while((lineTxt = bufferedReader.readLine()) != null){
                	++i;
                	System.out.println(i);
                	 // 使用分词器对中文句子进行分词，得到分词结果
            	     String[] words = factory.seg(lineTxt);
            	     String result="";
            	     for(String word : words) {
            	    	boolean tag =sw.isStopWord(word);
             	        if(!tag)result+=word+" ";
             	    }
            	    result+="\r\n";
           	     	bufferedWriter.write(result);
           	     	bufferedWriter.flush(); //刷新缓冲区的数据到文件
           	                     	
                }
                
            }else{
            	System.out.println("找不到指定的文件");
            }
	    } catch (Exception e) {
	    	System.out.println("读取文件内容出错");
	    	e.printStackTrace();
	    } finally {
	    	bufferedReader.close();
            bufferedWriter.close();
            reader.close();
            writer.close();
	    }

	}

}

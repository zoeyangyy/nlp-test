package nlp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.fnlp.nlp.cn.tag.POSTagger;
import org.fnlp.util.exception.LoadModelException;

public class postag {

	public static void main(String[] args) throws LoadModelException, IOException {

	    // 实例化分词对象
	    POSTagger postag = new POSTagger("/Users/zoe/Documents/nlp/models/pos.m");
	    
	    InputStreamReader reader = null;
	    BufferedReader bufferedReader =null;
	    OutputStreamWriter writer =null;
	    BufferedWriter bufferedWriter = null;
	    
	    try {
            String encoding="UTF-8";
            File file=new File("/Users/zoe/Documents/data/data_stopwords.txt");
            File refile=new File("/Users/zoe/Documents/data/data_postag.txt");
            if(!refile.exists()) refile.createNewFile();
            if(file.isFile() && file.exists()){ //判断文件是否存在
            	String lineTxt = null;
            	
                reader = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                bufferedReader = new BufferedReader(reader);
               
                writer = new OutputStreamWriter(new FileOutputStream(refile),encoding);//确认流的输出文件和编码格式
                bufferedWriter = new BufferedWriter(writer);
                
                int linenum=0;
                while((lineTxt = bufferedReader.readLine()) != null){
                	++linenum;
                	System.out.println(linenum);
                	
            	    String result="";
            	    //直接处理分好词的句子
        			String[] word = lineTxt.split(" ");
        			String[] tags = postag.tagSeged(word);
        			System.out.println(tags.length);
        			for(int i=0;i<tags.length;i++){
        				result+=word[i]+"("+tags[i]+") ";
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

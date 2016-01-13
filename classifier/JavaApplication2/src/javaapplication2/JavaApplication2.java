package javaapplication2;
import java.sql.*;
import java.math.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
public class JavaApplication2 {

     
    public static void main(String[] args) {
       
        String fileName; 
        String line;
        List<String> vocabulary = new ArrayList();
        boolean flag = true;
        
        int pos_vocabTot = 0;
        int neg_vocabTot = 0;
        int pos_vocab = 0;
        int neg_vocab = 0; 
        int vocabTot = 0;
        int infintitecount = 0;
        
        
        double prior_pos = 0.5;
            double prior_neg = 0.5;
            
            double prob_x_pos = 1.0;
            double prob_x_neg = 1.0;
            double prob_pos = 0.0;
            double prob_neg = 0.0;
                 
            int countResult = 0;

        
        // hashTable
        Hashtable pos_wordCountHash = new Hashtable(); 
        Hashtable neg_wordCountHash = new Hashtable(); 

        Enumeration hashKeys;
        Scanner user_input = new Scanner( System.in );
        
        
        try {
            
            for ( int i = 0; i <12500; i++)
            {
              for (int j = 0; j<= 10; j++)
              {
                  //fileName= "E:\\FYP\\aclImdb\\train\\neg\\test_" + j +".txt";
                  fileName= "E:\\FYP\\aclImdb\\train\\neg\\" + i + "_" + j +".txt";
                  Pattern p = Pattern.compile("[\\w']+");
                  Matcher myMatcher;// p.matcher(input)
                  try
                  {
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    System.out.println(fileName + ": file found");
                    
                    while((line = bufferedReader.readLine()) != null) 
                    {
                        myMatcher = p.matcher(line);
                        while ( myMatcher.find() ) 
                        {
                           flag = true;
                           String word = line.substring(myMatcher.start(), myMatcher.end());
                           word = word.toLowerCase();
                           vocabTot++;
                           neg_vocabTot++;
                   
                           int check = 0;
                           try { 
                               int checkx =  Integer.valueOf( String.valueOf(neg_wordCountHash.get(word) ) );
                               check = checkx;
                           }
                           catch (NumberFormatException ex) {
                               
                           }
                           if (check <= 0)
                           { 
                               int count = 1;
                               //vocabulary.add(word);
                               neg_wordCountHash.put(word,count);
                               neg_vocab++;     
                           }
                           else
                           {
                              neg_wordCountHash.put ( word, (1  + ( (int) neg_wordCountHash.get(word) ) ));
                           }
                           
                        }        
                    }
                    bufferedReader.close();
                  }
                  catch (FileNotFoundException ex)
                  {
                    //System.out.println(fileName + ": file not found");  
                  }
              }
            }
            
            for ( int i = 0; i <12500; i++)
            {
              for (int j = 0; j<= 10; j++)
              {
                  //fileName= "E:\\FYP\\aclImdb\\train\\pos\\test_" + j +".txt";
                  fileName= "E:\\FYP\\aclImdb\\train\\pos\\" + i + "_" + j +".txt";
                  Pattern p = Pattern.compile("[\\w']+");
                  Matcher myMatcher;// p.matcher(input)
                  try
                  {
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    System.out.println(fileName + ": file found");
                    
                    while((line = bufferedReader.readLine()) != null) 
                    {
                        myMatcher = p.matcher(line);
                        while ( myMatcher.find() ) 
                        {
                           flag = true;
                           String word = line.substring(myMatcher.start(), myMatcher.end());
                           word = word.toLowerCase();
                           vocabTot++;
                           pos_vocabTot++;
                   
                           int check = 0;
                           try { 
                               int checkx =  Integer.valueOf( String.valueOf(pos_wordCountHash.get(word) ) );
                               check = checkx;
                           }
                           catch (NumberFormatException ex) {
                               
                           }
                           if (check <= 0)
                           { 
                               int count = 1;
                               pos_wordCountHash.put(word,count);
                               pos_vocab++;
                           }
                           else
                           {
                              pos_wordCountHash.put ( word, (1  + ( (int) pos_wordCountHash.get(word) ) ));
                           }
                           
                        }        
                    }
                    bufferedReader.close();
                  }
                  catch (FileNotFoundException ex)
                  {
                    //System.out.println(fileName + ": file not found");  
                  }
              }
            
            }
           /* System.out.println("VoacbCount: " + vocabTot);
            System.out.println("pos_vocab: " + pos_vocab);
            System.out.println("neg_vocab: " + neg_vocab);
            System.out.println("pos_tot: " + pos_vocabTot);
            System.out.println("neg_tot: " + neg_vocabTot);
            
                            {
                               
                               double count_pos = 0;
                               double count_neg = 0;
                               try { 
                                    count_pos =  Double.valueOf( String.valueOf(pos_wordCountHash.get("i") ) );
                                    count_neg =  Double.valueOf( String.valueOf(neg_wordCountHash.get("i") ) );
                                }
                                catch (NumberFormatException ex) {
                               
                                }
                           
                                System.out.println( count_pos );                                
                                System.out.println( count_neg );
                                System.out.println("count_pos + 1: " + (count_pos + 1.0));
                                System.out.println("pos_vocabCount: " + pos_vocabTot);
                                System.out.println("pos_vocab: " + pos_vocab);
                                System.out.println("( (count_pos + 1.0) / (pos_vocabCount + pos_vocab) ): " + ( (count_pos + 1.0) / (pos_vocabTot + pos_vocab) ) );
                               
                                prob_x_pos = prob_x_pos * (Math.log( ((count_pos + 1.0) / (pos_vocabTot + pos_vocab)) ));
                                prob_x_neg = prob_x_neg * (Math.log( ((count_neg + 1.0) / (neg_vocabTot + neg_vocab)) ));
                                prob_pos = prob_x_pos * Math.log(prior_pos);
                                prob_neg = prob_x_neg * Math.log(prior_neg);
                          
                                System.out.println("********************************************");
                                System.out.println("Probability prob_pos: " + prob_pos);
                                System.out.println("Probability prob_neg: " + prob_neg);
                                System.out.println("********************************************");
                            }  */      
            



  
            
            hashKeys = neg_wordCountHash.keys();
            int hashLength = 0;
            while(hashKeys.hasMoreElements()) 
            {
                String str = (String) hashKeys.nextElement();
                System.out.println(str + ": " + neg_wordCountHash.get(str));
                hashLength++;
            }
            System.out.println("HashCount: " + hashLength);
            
            
            hashKeys = pos_wordCountHash.keys();
            hashLength = 0;
            while(hashKeys.hasMoreElements()) 
            {
                String str = (String) hashKeys.nextElement();
                System.out.println(str + ": " + pos_wordCountHash.get(str));
                hashLength++;
            }
                
            
            try 
            {
                String vocabFile = "E:\\FYP\\aclImdb\\vocabFile.txt";
                FileWriter fileWriter = new FileWriter(vocabFile);

                // Always wrap FileWriter in BufferedWriter.
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Note that write() does not automatically
                // append a newline character.
            
                for (String word : vocabulary) 
                {   
                    bufferedWriter.write(word);
                    bufferedWriter.newLine();
                }
            
                bufferedWriter.close();
            }
            catch (FileNotFoundException ex) 
            {
                System.out.println("cannot open vocabFile");
            }
            
            try 
            {   
                String hashFile = "E:\\FYP\\aclImdb\\Neg_hashWordCount.txt";
                FileWriter fileWriter = new FileWriter(hashFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                hashKeys = neg_wordCountHash.keys();
                while(hashKeys.hasMoreElements()) 
                {
                    String str = (String) hashKeys.nextElement();
                    int count = (int) neg_wordCountHash.get(str);
                    bufferedWriter.write(str);
                    bufferedWriter.write(" ");
                    bufferedWriter.write( String.valueOf(count));
                    bufferedWriter.newLine();
                }   
                bufferedWriter.close();
            }
            catch (FileNotFoundException ex) 
            {
                System.out.println("cannot open hashWordCount");
            }
            
            try 
            {   
                String hashFile = "E:\\FYP\\aclImdb\\Pos_hashWordCount.txt";
                FileWriter fileWriter = new FileWriter(hashFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                hashKeys = pos_wordCountHash.keys();
                while(hashKeys.hasMoreElements()) 
                {
                    String str = (String) hashKeys.nextElement();
                    int count = (int) pos_wordCountHash.get(str);
                    bufferedWriter.write(str);
                    bufferedWriter.write(" ");
                    bufferedWriter.write( String.valueOf(count));
                    bufferedWriter.newLine();
                }   
                bufferedWriter.close();
            }
            catch (FileNotFoundException ex) 
            {
                System.out.println("cannot open hashWordCount");
            } 
           

            /*double prior_pos = 0.5;
            double prior_neg = 0.5;
            
            double prob_x_pos = 1.0;
            double prob_x_neg = 1.0;
            double prob_pos = 0.0;
            double prob_neg = 0.0;
                 
            int countResult = 0;*/
            /*for ( int i = 0; i < 12500; i++)
            {
             prior_pos = 0.5;
             prior_neg = 0.5;
            
             prob_x_pos = 1.0;
             prob_x_neg = 1.0;
             prob_pos = 0.0;
             prob_neg = 0.0;
             double documentProp = 0.0;
            
             
             for (int j = 0; j<= 10; j++)
             {
                fileName= "E:\\FYP\\aclImdb\\test\\pos\\" + i + "_" + j +".txt";
                Pattern p = Pattern.compile("[\\w']+");
                Matcher myMatcher;// p.matcher(input)
                try
                {
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    System.out.println(fileName + ": file found");
                    
             
                    while((line = bufferedReader.readLine()) != null) 
                    {
                            myMatcher = p.matcher(line);
                            while ( myMatcher.find() ) 
                            {
                               String word = line.substring(myMatcher.start(), myMatcher.end());
                               word = word.toLowerCase();
                           
                               
                               double count_pos = 0;
                               double count_neg = 0;
                               try { 
                                    count_pos =  Double.valueOf( String.valueOf(pos_wordCountHash.get(word) ) );
                                    count_neg =  Double.valueOf( String.valueOf(neg_wordCountHash.get(word) ) );
                                }
                                catch (NumberFormatException ex) {
                               
                                }
                           
                                //System.out.println( count_pos );                                //System.out.println( count_neg );
                                //System.out.println("count_pos + 1: " + (count_pos + 1.0));
                                //System.out.println("pos_vocabCount: " + pos_vocabCount);
                                //System.out.println("pos_vocab: " + pos_vocab);
                                //System.out.println("( (count_pos + 1.0) / (pos_vocabCount + pos_vocab) ): " + ( (count_pos + 1.0) / (pos_vocabCount + pos_vocab) ) );
                               
                                prob_x_pos = prob_x_pos * (Math.log( ((count_pos + 1.0) / (pos_vocabTot + pos_vocab)) ));
                                prob_x_neg = prob_x_neg * (Math.log( ((count_neg + 1.0) / (neg_vocabTot + neg_vocab)) )); 
                          
                                                    //System.out.println("prob_x_pos: " + prob_x_pos);
                            }        
                    }
                    bufferedReader.close();
                    prob_pos = prob_x_pos * (prior_pos);
                    prob_neg = prob_x_neg * (prior_neg);
                    
                    if ( prob_neg == Double.POSITIVE_INFINITY || prob_neg == Double.NEGATIVE_INFINITY)
                    {
                        
                        infintitecount++;
                    }
                    if ( prob_pos > prob_neg )
                    {
                        System.out.println("********************************************");
                        System.out.println("Probability prob_pos: " + prob_pos);
                        System.out.println("Probability prob_neg: " + prob_neg);
                        System.out.println("********************************************");
                        countResult++;
                    }
                    
                    
                }
                
                catch (FileNotFoundException e )
                {
                    
                }
             }
            }
            System.out.println("Infinite Count: "+ infintitecount);
            System.out.println("Result Count: "+ countResult);*/
            
            
             
           
            /* Pattern p = Pattern.compile("[\\w']+");
             Matcher myMatcher;// p.matcher(input)

             /// testing my input 
             
             
                prior_pos = 0.5;
                prior_neg = 0.5;
            
                prob_x_pos = 1.0;
                prob_x_neg = 1.0;
                prob_pos = 0.0;
                prob_neg = 0.0;
                
                String Name;
                
               
                try
                {
                    prior_pos = 0.5;
                    prior_neg = 0.5;
            
                    prob_x_pos = 1.0;
                    prob_x_neg = 1.0;
                    prob_pos = 0.0;
                    prob_neg = 0.0;
                    String linex;
                  do
                  {
                    System.out.println("Enter file Name: ");
                    Name = user_input.next();
                    fileName= "E:\\FYP\\aclImdb\\test\\pos\\"+Name+".txt";
                    
                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    System.out.println(fileName + ": file found");
                    
                    while((linex = bufferedReader.readLine()) != null) 
                    {
                        myMatcher = p.matcher(linex);
                        while ( myMatcher.find() ) 
                        {
                           String word = linex.substring(myMatcher.start(), myMatcher.end());
                           word = word.toLowerCase();
                           
                           System.out.println( word );
                           double count_pos = 0;
                           double count_neg = 0;
                           try { 
                               count_pos =  Double.valueOf( String.valueOf(pos_wordCountHash.get(word) ) );
                               count_neg =  Double.valueOf( String.valueOf(neg_wordCountHash.get(word) ) );
                           }
                           catch (NumberFormatException ex) {
                               
                           }
                        
                           System.out.println("count_pos + 1: " + (count_pos + 1.0));
                           System.out.println("count_new + 1: " + (count_neg + 1.0));
                           System.out.println("pos_vocabCount: " + pos_vocabTot);
                           System.out.println("pos_vocab: " + pos_vocab);
                           System.out.println("( (count_pos + 1.0) / (pos_vocabCount + pos_vocab) ): " + ( (count_pos + 1.0) / (pos_vocabTot + pos_vocab) ) );
                          
                           prob_x_pos = prob_x_pos * (Math.log( ((count_pos + 1.0) / (pos_vocabTot + pos_vocab)) ));
                           prob_x_neg = prob_x_neg * (Math.log( ((count_neg + 1.0) / (neg_vocabTot + neg_vocab)) ));
                        
                       }        
                    }

                    prob_pos =  prob_x_pos * Math.log(prior_pos);
                    prob_neg =  prob_x_neg * Math.log(prior_neg);
             
                    System.out.println("Probabi;ity for class possitive: " + prob_pos);
                    System.out.println("Probabi;ity for class negetive: " + prob_neg);
                    fileReader.close();
                  } while ( Name != "1");
                } 
                
                catch (FileNotFoundException e){
                    
                }*/
        }
        
        
        catch (Exception e)
        {
            System.out.println(e);
        }
        
    }
    
}

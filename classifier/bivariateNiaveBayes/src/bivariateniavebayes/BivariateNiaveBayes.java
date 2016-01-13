

package bivariateniavebayes;
import java.sql.*;
import java.math.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;


public class BivariateNiaveBayes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
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
                  catch (FileNotFoundException ex){ }
                  
              }// end of inner loop
            }// end of outer loop
            
            
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
                           catch (NumberFormatException ex) { }
                           if (check <= 0)
                           { 
                               int count = 1;
                               pos_wordCountHash.put(word,count);
                               pos_vocab++;
                           }
                           else
                              pos_wordCountHash.put ( word, (1  + ( (int) pos_wordCountHash.get(word) ) )); 
                        }        
                    }
                    bufferedReader.close();
                  }
                  catch (FileNotFoundException ex){ }
                  
              }// end of innner loop
            } // end of outer loop 
            
            // bivariate naive bayes
            /*for ( int i = 0; i < 12500; i++)
            {
             prior_pos = 0.5;
             prior_neg = 0.5;
            
             prob_x_pos = 1.0;
             prob_x_neg = 1.0;
             prob_pos = 0.0;
             prob_neg = 0.0;
             //double documentProp = 0.0;
            
             Hashtable bi_wordCountHash = new Hashtable(); 
             for (int j = 0; j<= 10; j++)
             {
                fileName= "E:\\FYP\\aclImdb\\test\\neg\\" + i + "_" + j +".txt";
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
                               int check = 0;
                               try { 
                                int checkx =  Integer.valueOf( String.valueOf(bi_wordCountHash.get(word) ) );
                                check = checkx;
                               }
                               catch (NumberFormatException ex) { }
                               if (check <= 0)
                               { 
                                int count = 1;
                                bi_wordCountHash.put(word,count);
                                
                                double count_pos = 0;
                                double count_neg = 0;
                                try { 
                                    count_pos =  Double.valueOf( String.valueOf(pos_wordCountHash.get(word)) );
                                    count_neg =  Double.valueOf( String.valueOf(neg_wordCountHash.get(word)) );
                                }
                                catch (NumberFormatException ex) { }
                    
                                prob_x_pos = prob_x_pos * (Math.log( ((count_pos + 1.0) / (pos_vocabTot + pos_vocab)) ));
                                prob_x_neg = prob_x_neg * (Math.log( ((count_neg + 1.0) / (neg_vocabTot + neg_vocab)) ));
                               }
                            }        
                    }
                    bufferedReader.close();
                }
                catch (FileNotFoundException e ){ }
             }
                    prob_pos = prob_x_pos * (prior_pos);
                    prob_neg = prob_x_neg * (prior_neg);
                   
                    if ( prob_neg == Double.POSITIVE_INFINITY || prob_neg == Double.NEGATIVE_INFINITY)
                        infintitecount++;
                
                    if ( prob_pos < prob_neg )
                    {
                        System.out.println("********************************************");
                        System.out.println("Probability prob_pos: " + prob_pos);
                        System.out.println("Probability prob_neg: " + prob_neg);
                        System.out.println("********************************************");
                        countResult++;
                    }
            }// outer loop end
            
            
            System.out.println("Infinite Count: "+ infintitecount);
            System.out.println("Result Count: "+ countResult);
            
            /*String Name;
            Pattern p = Pattern.compile("[\\w']+");
            Matcher myMatcher;// p.matcher(input)
            try
                { 
                  do
                  {
                    prior_pos = 0.5;
                    prior_neg = 0.5;
            
                    prob_x_pos = 1.0;
                    prob_x_neg = 1.0;
                    prob_pos = 0.0;
                    prob_neg = 0.0;
                    String linex;
                    
                    Hashtable bi_wordCountHash = new Hashtable(); 
                    
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
                               int check = 0;
                               try { 
                                int checkx =  Integer.valueOf( String.valueOf(bi_wordCountHash.get(word) ) );
                                check = checkx;
                               }
                               catch (NumberFormatException ex) { }
                               if (check <= 0)
                               { 
                                int count = 1;
                                bi_wordCountHash.put(word,count);
                                
                                double count_pos = 0;
                                double count_neg = 0;
                                try { 
                                    count_pos =  Double.valueOf( String.valueOf(pos_wordCountHash.get(word)) );
                                    count_neg =  Double.valueOf( String.valueOf(neg_wordCountHash.get(word)) );
                                }
                                catch (NumberFormatException ex) { }
                    
                                prob_x_pos = prob_x_pos * (Math.log( ((count_pos + 1.0) / (pos_vocabTot + pos_vocab)) ));
                                prob_x_neg = prob_x_neg * (Math.log( ((count_neg + 1.0) / (neg_vocabTot + neg_vocab)) ));
                               }
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
    
}

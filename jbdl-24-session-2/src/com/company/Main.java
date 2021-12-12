package com.company;
import com.company.MT.Count;
import com.company.MT.EvenCounter;
import com.company.MT.OddCounter;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Set
       // System.out.println("-hash set-") ;
       // fn(new HashSet<>());
      //  System.out.println("-tree set-") ;
//        fn(new TreeSet<>(new Comparator<Rating>() {
//            @Override
//            public int compare(final Rating o1, final Rating o2) {
//                return o1.getCriticRating()-o2.getCriticRating();
//            }
//        }));
       // System.out.println("-linkedhash set-") ;
       // fn(new LinkedHashSet<>());
       // System.out.println("-priority queue-") ;
        Queue<Rating> ratingQueue = new PriorityQueue<Rating>(new Comparator<Rating>(){
            @Override
            public int compare(final Rating o1, final Rating o2) {
                return o2.getCriticRating() - o1.getCriticRating();
            }
        });
        //fn3(ratingQueue);

//        Scanner sc  = new Scanner(System.in);
//        System.out.println(sc.nextLine()) ;
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        try {
//            System.out.println( br.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File file = new File("test.txt") ;
//        FileOutputStream fileOutputStream = null;
//        try {
//             fileOutputStream = new FileOutputStream(file, true);
//            fileOutputStream.write(br.readLine().getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            System.out.println(br.readLine());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Thread t1 = new Thread(new OddCounter(10));
//        t1.start();
//        Thread t2 = new Thread(new OddCounter(10));
//        t2.start();
//
         //Thread pool
//        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
//        List<Callable<Integer>> tasks = new ArrayList<>();
//        for(int i = 0;i<1000;i++)
//            tasks.add(new CounterCallbale());
//        try {
//            List<Future<Integer>> futures =  threadPool.invokeAll(tasks, 100, TimeUnit.MILLISECONDS);
//            for(Future<Integer> future : futures){
//                if(!future.isCancelled()){
//                    System.out.println(future.get());
//                }
//            }
//        } catch (InterruptedException e) {
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }finally {
//            threadPool.shutdown();
//        }


        // There are two threads 1 prints odd value, another prints event value.
        //1,3,5,7...
        //2,4,6,...
        //Our task is to print values like 1,2,3,4,5..N
        Count count = new Count(500000,0);
        Thread evenT = new Thread(new EvenCounter(count));
        Thread oddT = new Thread(new OddCounter(count));
        evenT.start();
        oddT.start();
    }

    static void fn1(Set<Integer> set){
        set.add(500);
        set.add(12);
        set.add(44);
        set.add(221);
        set.add(31);
        set.add(31);


        for(Integer el : set){
            System.out.println(el);
        }

        if(set.contains(500))
        {
            System.out.println("500 is present");
        }

    }


    static void fn(Set<Rating> set){
        set.add(new Rating(1,1));
        set.add(new Rating(4,3));
        set.add(new Rating(2,5));
        set.add(new Rating(4,4));
        set.add(new Rating(5,3));
        set.add(new Rating(3,2));


        for(Rating el : set){
            System.out.print(el.getUserRating() + " " + el.getCriticRating());
            System.out.println();
        }
    }


    static void fn3(Queue<Rating> queue){
        queue.add(new Rating(1,1));
        queue.add(new Rating(4,3));
        queue.add(new Rating(2,5));
        queue.add(new Rating(4,4));
        queue.add(new Rating(5,3));
        queue.add(new Rating(3,2));

        System.out.println("Element with highest priority is : "+ queue.peek().getUserRating() + " "+ queue.peek().getCriticRating());
        for(Rating el : queue){
            System.out.print(el.getUserRating() + " " + el.getCriticRating());
            System.out.println();
        }
    }
}

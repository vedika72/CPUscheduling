//Vedika Bansal
//1710110379
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VEDIKA BANSAL
 */
public class CPUscheduling implements ActionListener{
        JComboBox cb = new JComboBox(new String[]{"1","2","3","4","5","6"});
        JFrame in = new JFrame();                       //Input Frame
        
        static JTextField tf1 = new JTextField();       //creating text fields for user input
        static JTextField tf2 = new JTextField();       //static to make them accessible globally
        JTextField tf3 = new JTextField();
        static JTextField tf4 = new JTextField();
        JTextField tf5 = new JTextField();
        static JTextField tf6cb1 = new JTextField();
        static JTextField tf7cb1 = new JTextField();
        static JTextField tf6cb2 = new JTextField();
        static JTextField tf7cb2 = new JTextField();
        static JTextField tf6cb3 = new JTextField();
        static JTextField tf7cb3 = new JTextField();
        static JTextField tf6cb4 = new JTextField();
        static JTextField tf7cb4 = new JTextField();
        static JTextField tf6cb5 = new JTextField();
        static JTextField tf7cb5 = new JTextField();
        static JTextField tf6cb6 = new JTextField();
        static JTextField tf7cb6 = new JTextField();

        JLabel no = new JLabel("No. of Procedures:");    //creating JLabel objects
        JLabel bt = new JLabel("Burst Time:");
        JLabel at = new JLabel("Arrival Time:");
        JLabel pr = new JLabel("Priorities (lower the number, higher the priority):");
        JLabel tq = new JLabel("Time Quantum:");
        
        JLabel gt = new JLabel("Gantt Chart:");
        JLabel wt = new JLabel("Waiting Time:");
        JLabel tat = new JLabel("Turn-around Time:");
        
        JCheckBox cb1 = new JCheckBox("FCFS");              //declaring checkboxes
        JCheckBox cb2 = new JCheckBox("Round Robin");
        JCheckBox cb3 = new JCheckBox("Preemptive-SJF");
        JCheckBox cb4 = new JCheckBox("Non-preemptive-SJF");
        JCheckBox cb5 = new JCheckBox("Preemptive-Priority");
        JCheckBox cb6 = new JCheckBox("Non-preemptive-Priority");

        JButton comp = new JButton("COMPUTE");              //compute button for final execution
                  
        Draw dp1;      
        Draw dp2;
        Draw dp3;
        Draw dp4;
        Draw dp5;
        Draw dp6;

        static ArrayList<Integer> arrbt = new ArrayList<Integer>(50);   //to store contents 
        static ArrayList<Integer> arrat = new ArrayList<Integer>(50);   //to store contents 
        static ArrayList<Integer> arrpr = new ArrayList<Integer>(50);   //to store contents 
        static ArrayList<Integer> arrtq = new ArrayList<Integer>(50);   //to store contents 
        static int selected[];
        
    CPUscheduling(){
        
        in.setSize(500, 500);   //frame specifications
        in.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        in.setVisible(true);
        in.setTitle("Input Screen");
        in.setLayout(new GridLayout(2,1));
        
        JPanel p = new JPanel();                            //creating panels
        JPanel pc = new JPanel();
        p.setLayout(new GridLayout(8,2));
        p.setSize(400,400);
        p.add(no);                      //adding labels and text fields to panel
        p.add(cb);
        p.add(bt);
        p.add(tf1);
        p.add(at);
        p.add(tf2);
        p.add(pr);
        p.add(tf3);
        p.add(tq);
        p.add(tf4);
        
        p.add(cb1);
        p.add(cb2);
        p.add(cb3);
        p.add(cb4);
        p.add(cb5);
        p.add(cb6);
        in.add(p);
        
        p.setSize(50,40);
        comp.setSize(50, 10);
        pc.add(comp);                   //compute added to another panel
        in.add(pc);
        comp.addActionListener(this);   //add action listener o compute button
   }
    public static void main(String args[]){
        CPUscheduling f = new CPUscheduling();      //consructor call
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int length;     
        String count = cb.getActionCommand();  
        String sbt = tf1.getText();
        String sat = tf2.getText();
        String spr = tf3.getText();
        int nop = Integer.parseInt((String) cb.getSelectedItem());
        
        String sub;
        JFrame out = new JFrame();                  //creates new frame for output
        int j1 =-1, j2 =-1, j3 = -1;
        int index = 0;                                            //storing bt, at, priorities in separate arraylists
        for(int i = 0; i < sbt.length(); i++){  
            if(sbt.charAt(i)==',' || sbt.charAt(i)=='.'){
                sub = sbt.substring(j1+1, i);
                if(index<nop)
                    arrbt.add(Integer.parseInt(sub));
                j1 = i;
                index++;
            }  
        }
        index = 0;
        for(int i = 0; i < sat.length(); i++){
           if(sat.charAt(i)==','|| sat.charAt(i)=='.'){
                sub = sat.substring(j2+1, i);
                if(index<nop)
                    arrat.add(Integer.parseInt(sub));
                j2 = i;
                index++;
            }   
        }
        index = 0;
        for(int i = 0; i < spr.length(); i++){           
           if(spr.charAt(i)==','|| spr.charAt(i)=='.'){
                sub = spr.substring(j3+1, i);
                if(index<nop)
                    arrpr.add(Integer.parseInt(sub));
                j3 = i;
                index++;
            }  
        }
        
        System.out.println(); 
        //out.setSize(1200, 1200); 
        out.setExtendedState(JFrame.MAXIMIZED_BOTH);//output frame specifications
        out.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        out.setVisible(true);
        out.setTitle("Output Screen");
        out.setLayout(new GridLayout(6,1));        //choosing layout
        Random r = new Random();
        float rc;
        float gc;
        float bc;
        if(cb1.isSelected()){
            JPanel p1 = new JPanel();               //separate panel creation for fcfs
            rc = r.nextFloat();
            gc = r.nextFloat();
            bc = r.nextFloat();
            p1.setBackground(new Color(rc,gc,bc));
            p1.setBorder(BorderFactory.createTitledBorder("FCFS"));
           
            selected = new int[]{1,0,0,0,0,0};
            tf6cb1 = new JTextField();
            tf7cb1 = new JTextField(); 
            JLabel gt = new JLabel("Gantt Chart:");
            JLabel wt = new JLabel("Waiting Time:");
            JLabel tat = new JLabel("Turn-around Time:");     
            
            p1.add(gt);
            dp1 = new Draw(selected);
            p1.add(dp1);
            p1.add(wt);
            p1.add(tf6cb1);
            p1.add(tat);
            p1.add(tf7cb1);
            dp1.repaint();
            p1.setLayout(new GridLayout(3,2));
            out.add(p1);
        }
        if(cb2.isSelected()){                   
            JPanel p2 = new JPanel();                   //separate panel creation for rr
            rc = r.nextFloat();
            gc = r.nextFloat();
            bc = r.nextFloat();
            p2.setBackground(new Color(rc,gc,bc));
            p2.setBorder(BorderFactory.createTitledBorder("Round Robin"));
            JTextField tf5 = new JTextField();
            tf6cb2 = new JTextField();
            tf7cb2 = new JTextField(); 
            JLabel gt = new JLabel("Gantt Chart:");
            JLabel wt = new JLabel("Waiting Time:");
            JLabel tat = new JLabel("Turn-around Time:");
            
            selected = new int[]{0,1,0,0,0,0};
            dp2 = new Draw(selected);
            p2.add(gt);
            p2.add(dp2);
            p2.add(wt);
            p2.add(tf6cb2);
            p2.add(tat);
            p2.add(tf7cb2);
            dp2.repaint();
            p2.setLayout(new GridLayout(3,2));
            out.add(p2);
        }    
        if(cb3.isSelected()){
            JPanel p3 = new JPanel();                       //separate panel creation for preemptive sjf
            rc = r.nextFloat();
            gc = r.nextFloat();
            bc = r.nextFloat();
            p3.setBackground(new Color(rc,gc,bc));
            p3.setBorder(BorderFactory.createTitledBorder("Preemptive-SJF"));
            JTextField tf5 = new JTextField();
            tf6cb3 = new JTextField();
            tf7cb3 = new JTextField(); 
            JLabel gt = new JLabel("Gantt Chart:");
            JLabel wt = new JLabel("Waiting Time:");
            JLabel tat = new JLabel("Turn-around Time:");
            
            selected = new int[]{0,0,1,0,0,0};
            dp3 = new Draw(selected);
            p3.add(gt);
            p3.add(dp3);
            p3.add(wt);
            p3.add(tf6cb3);
            p3.add(tat);
            p3.add(tf7cb3);
            dp3.repaint();
            p3.setLayout(new GridLayout(3,2));
            out.add(p3);
        }
        if(cb4.isSelected()){
            JPanel p4 = new JPanel();                       //separate panel creation for non-preemptive sjf
            rc = r.nextFloat();
            gc = r.nextFloat();
            bc = r.nextFloat();
            p4.setBackground(new Color(rc,gc,bc));
            p4.setBorder(BorderFactory.createTitledBorder("Non-preemptive-SJF"));
            JTextField tf5 = new JTextField();
            tf6cb4 = new JTextField();
            tf7cb4 = new JTextField(); 
            JLabel gt = new JLabel("Gantt Chart:");
            JLabel wt = new JLabel("Waiting Time:");
            JLabel tat = new JLabel("Turn-around Time:");
            
            selected = new int[]{0,0,0,1,0,0};
            dp4 = new Draw(selected);
            p4.add(gt);
            p4.add(dp4);
            p4.add(wt);
            p4.add(tf6cb4);
            p4.add(tat);
            p4.add(tf7cb4);
            dp4.repaint();
            p4.setLayout(new GridLayout(3,2));
            out.add(p4);
        }
        if(cb5.isSelected()){
            JPanel p5 = new JPanel();                           //separate panel creation for preemptive priority
            rc = r.nextFloat();
            gc = r.nextFloat();
            bc = r.nextFloat();
            p5.setBackground(new Color(rc,gc,bc));            
            p5.setBorder(BorderFactory.createTitledBorder("Preemptive-Priority"));
            JTextField tf5 = new JTextField();
            tf6cb5 = new JTextField();
            tf7cb5 = new JTextField(); 
            JLabel gt = new JLabel("Gantt Chart:");
            JLabel wt = new JLabel("Waiting Time:");
            JLabel tat = new JLabel("Turn-around Time:");
         
            selected = new int[]{0,0,0,0,1,0};
            dp5 = new Draw(selected);
            p5.add(gt);
            p5.add(dp5);
            p5.add(wt);
            p5.add(tf6cb5);
            p5.add(tat);
            p5.add(tf7cb5);
            dp5.repaint();
            p5.setLayout(new GridLayout(3,2));
            out.add(p5);    
        }
        if(cb6.isSelected()){
            JPanel p6 = new JPanel();                               //separate panel creation for non preemptive priority
            rc = r.nextFloat();
            gc = r.nextFloat();
            bc = r.nextFloat();
            p6.setBackground(Color.green);            
            p6.setBorder(BorderFactory.createTitledBorder("Non-preemptive-Priority"));
            JTextField tf5 = new JTextField();
            tf6cb6 = new JTextField();
            tf7cb6 = new JTextField(); 
            JLabel gt = new JLabel("Gantt Chart:");
            JLabel wt = new JLabel("Waiting Time:");
            JLabel tat = new JLabel("Turn-around Time:");
            
            selected = new int[]{0,0,0,0,0,1};
            dp6 = new Draw(selected);
            p6.add(gt);
            p6.add(dp6);
            p6.add(wt);
            p6.add(tf6cb6);
            p6.add(tat);
            p6.add(tf7cb6);
            dp6.repaint();
            p6.setLayout(new GridLayout(3,2));
            out.add(p6);    
        }   
    }
}
    //Class for drawing gantt charts
    class Draw extends JPanel{
        int sel[];
        public static void main(String []a){
            
        }
        Draw (int s[]){
            sel = s;
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            int xCoor = 0;
            int yCoor = 20;
            g.setColor(Color.black);
            g.drawRect(0, 0, 400, 20);              //for storing respective arrays
            ArrayList<Integer> burst = new ArrayList<Integer>(50);
            ArrayList<Integer> arrival = new ArrayList<Integer>(50);
            ArrayList<Integer> waiting = new ArrayList<Integer>(50);
            ArrayList<Integer> turnAround = new ArrayList<Integer>(50);
            ArrayList<String> process = new ArrayList<String>();
            if(sel[0]==1){                          //if fcfs selected
                
                int clock = 0;
                int c=0;                //iterator for fcfs array list
                xCoor = 0;
                burst = new ArrayList<Integer>(50);
                arrival = new ArrayList<Integer>(50);
                waiting = new ArrayList<Integer>(50);
                turnAround = new ArrayList<Integer>(50);
                process = new ArrayList<String>();
                int totalBurst = 0;     //calculate total burst time
                do{
                    int index = 0;                  //of the initial sequence
                    for(Integer j : CPUscheduling.arrat){
                                                       //adding all processes at a particular arrival time denoted by clock
                        if(clock==j.intValue()){      //to add the corresponding details of processes in ascending order of their arrival times
                            burst.add(CPUscheduling.arrbt.get(index));
                            process.add("P"+Integer.toString(index+1));
                            arrival.add(clock);
                            System.out.println("burst"+burst.get(c));
                            System.out.println("process:"+process.get(c));
                            System.out.println("arrival"+arrival.get(c));
                            totalBurst+= burst.get(c);
                            c++;
                        } 
                        index++;
                    }clock++;
                }while(clock<=Collections.max(CPUscheduling.arrat));
                                                                     //to check
                System.out.println("sum"+totalBurst);
                System.out.println("bs"+burst.size());
                System.out.print("xc"+xCoor);
                                                        //for gantt chart
                for(int i =0; i<burst.size(); i++){
                        g.drawLine((burst.get(i)*400/totalBurst) + xCoor, 0,(burst.get(i)*400/totalBurst) + xCoor , 20);
                        xCoor = xCoor + burst.get(i)*400/totalBurst;
                }
                xCoor = 0;
                int sum = 0;
                g.drawString(Integer.toString(sum),xCoor, 30);
                for(int i =0; i<burst.size(); i++){
                    g.drawString(process.get(i), xCoor+1, 15);
                    sum+=burst.get(i);
                    g.drawString(Integer.toString(sum), (burst.get(i)*400/totalBurst) + xCoor, 30);
                    xCoor = xCoor + burst.get(i)*400/totalBurst;
                    turnAround.add(sum-arrival.get(i));
                    waiting.add(turnAround.get(i)-burst.get(i));
                }
                
                    //for printing lists of waiting and turn around times
                    int t;
                    String ts;
                    int min;            //sorting according to the processes to final print waiting and turnaround time
                      for(int k=0; k<process.size(); k++){
                    min=k;
                    for(int l=(k+1);l<process.size();l++){
                        System.out.println(Integer.parseInt((process.get(l)).substring(1)));
                        if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                min = l;
                    }
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                    t = waiting.get(min);
                    waiting.set(min, waiting.get(k));
                    waiting.set(k, t);
                    
                    t = turnAround.get(min);
                    turnAround.set(min, turnAround.get(k));
                    turnAround.set(k, t);
                }
                      System.out.println(waiting.get(1));
                      String subs1 = "";
                      String subs2 = "";
                      for( int i=0; i<process.size(); i++){
                          subs1 = subs1.concat(process.get(i)+"="+waiting.get(i)+",");
                          subs2 = subs2.concat(process.get(i)+"="+turnAround.get(i)+",");
                      }
                      double avWt=0.0, avTat=0.0;
                      for(int i=0; i<waiting.size(); i++){
                          avWt = avWt+waiting.get(i);
                          avTat = avTat+turnAround.get(i);
                      }
                      subs1 = subs1.concat("           Average Waiting Time:   "+avWt/waiting.size());
                      subs2 = subs2.concat("           Average Turn Around Time:"+avTat/waiting.size());
                      
                CPUscheduling.tf6cb1.setText(subs1);
                CPUscheduling.tf7cb1.setText(subs2);
            }
            if(sel[1]==1){                  //if round robin selected
               
                int clock = 0;
                int c=0;                
                xCoor = 0;
                burst = new ArrayList<Integer>(50);
                arrival = new ArrayList<Integer>(50);
                waiting = new ArrayList<Integer>(50);
                turnAround = new ArrayList<Integer>(50);
                process = new ArrayList<String>();
                ArrayList<Integer> burstr = new ArrayList<Integer>(50);
                int totalBurst = 0;     //calculate total burst time
                do{
                    int index = 0;                  //of the initial sequence
                    for(Integer j : CPUscheduling.arrat){
                                                       //adding all processes at a particular arrival time denoted by clock
                        if(clock==j.intValue()){      //to add the corresponding details of processes in ascending order of their arrival times
                            burst.add(CPUscheduling.arrbt.get(index));
                            burstr.add(CPUscheduling.arrbt.get(index));
                            process.add("P"+Integer.toString(index+1));
                            arrival.add(clock);
                            System.out.println("burst"+burst.get(c));
                            System.out.println("process:"+process.get(c));
                            totalBurst+= burst.get(c);
                            c++;
                        } 
                        index++;
                    }clock++;
                }while(clock<=Collections.max(CPUscheduling.arrat));
                                                                     //to check
                System.out.println("sum"+totalBurst);
                System.out.println("bs"+burst.size());
             
                //arrays for gantt chart of rr
                ArrayList<Integer> burstrr = new ArrayList<Integer>(50);
                ArrayList<Integer> completion = new ArrayList<Integer>(50);
                ArrayList<Integer> trr = new ArrayList<Integer>(50);
                ArrayList<String> processrr = new ArrayList<String>();
                
                for(int i=0;i<CPUscheduling.arrbt.size();i++){
                    completion.add(0);
                }
                int i=0,clk=0;
                int q = Integer.parseInt(CPUscheduling.tf4.getText());      //collecting time quantum
                do{                                                         //for gantt chart array
                    for(int j=0;j<burst.size();j++){
                        if(burst.get(j)==0){
                            continue;
                        }
                        else 
                        if(burst.get(j)!=0&&burst.get(j)<=q){
                            burstrr.add(burst.get(j));
                            processrr.add(process.get(j));
                            clk = clk + burst.get(j);
                            burst.set(j, 0);
                            completion.set(j,clk);
                            trr.add(clk);
                            System.out.println("Burst"+ process.get(j) +"becomes"+burst.get(j));
                            System.out.println("t1"+i+"becomes"+trr.get(i));
                        }
                        else
                        {
                            burstrr.add(q);
                            processrr.add(process.get(j));
                            burst.set(j, burst.get(j)-q);
                            clk = clk +q;
                            trr.add(clk);
                            System.out.println("Burst"+ process.get(j) +"becomes"+burst.get(j));
                            System.out.println("t2"+i+"becomes"+trr.get(i));
                        }
                        i++;    
                    }
                }while(clk<totalBurst);
                
                int sum = 0;
                g.drawString(Integer.toString(sum),xCoor, 30);
                for(i =0; i<burstrr.size(); i++){
                    if(burstrr.get(i)!=0){
                        g.drawString(processrr.get(i), xCoor+1, 15);
                        g.drawLine((burstrr.get(i)*400/totalBurst) + xCoor, 0,(burstrr.get(i)*400/totalBurst) + xCoor , 20);
                        
                        sum+=burstrr.get(i);
                        g.drawString(Integer.toString(sum), (burstrr.get(i)*400/totalBurst) + xCoor, 30);
                        xCoor = xCoor + burstrr.get(i)*400/totalBurst;
                    }
                }
                 for(Integer n1 : completion){
                    System.out.print("completion :"+n1);   
                }
                
                    int t;
                    String ts;
                    int min;
                      for(int k=0; k<process.size(); k++){
                    min=k;
                    for(int l=(k+1);l<process.size()-1;l++){
                        if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                min = l;
                    }
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                    t = completion.get(min);
                    completion.set(min, completion.get(k));
                    completion.set(k, t);
                    
                    t = burstr.get(min);
                    burstr.set(min, burstr.get(k));
                    burstr.set(k, t);
                    
                    t = arrival.get(min);
                    arrival.set(min, arrival.get(k));
                    arrival.set(k, t);
                }
                                            //substrings for text for waiting and turn around time
                      String subs1 = "";
                      String subs2 = "";
                      for(i=0; i<process.size(); i++){
                          turnAround.add(completion.get(i)-arrival.get(i));
                          waiting.add(turnAround.get(i)-burstr.get(i));
                          subs1 = subs1.concat(process.get(i)+"="+waiting.get(i)+",");
                          subs2 = subs2.concat(process.get(i)+"="+turnAround.get(i)+",");
                      }
                      double avWt=0.0, avTat=0.0;
                      for(i=0; i<waiting.size(); i++){
                          avWt = avWt+waiting.get(i);
                          avTat = avTat+turnAround.get(i);
                      }
                      subs1 = subs1.concat("           Average Waiting Time:   "+avWt/waiting.size());
                      subs2 = subs2.concat("           Average Turn Around Time:"+avTat/waiting.size());
                      
                CPUscheduling.tf6cb2.setText(subs1);
                CPUscheduling.tf7cb2.setText(subs2);
                
            }
            
            
           if(sel[2]==1){                           //preemptive sjf 
                
                int clock = 0;
                int c=0;                
                xCoor = 0;
                burst = new ArrayList<Integer>(50);
                arrival = new ArrayList<Integer>(50);
                waiting = new ArrayList<Integer>(50);
                turnAround = new ArrayList<Integer>(50);
                process = new ArrayList<String>();
                ArrayList<Integer> bursts = new ArrayList<Integer>(50);
                int totalBurst = 0;     //calculate total burst time
                do{
                    int index = 0;                  //of the initial sequence
                    for(Integer j : CPUscheduling.arrat){
                                                       //adding all processes at a particular arrival time denoted by clock
                        if(clock==j.intValue()){      //to add the corresponding details of processes in ascending order of their arrival times
                            burst.add(CPUscheduling.arrbt.get(index));
                            bursts.add(CPUscheduling.arrbt.get(index));
                            process.add("P"+Integer.toString(index+1));
                            arrival.add(clock);
                            System.out.println("burst"+burst.get(c));
                            System.out.println("process:"+process.get(c));
                            System.out.println("arrival"+arrival.get(c));
                            totalBurst+= burst.get(c);
                            c++;
                        } 
                        index++;
                    }clock++;
                }while(clock<=Collections.max(CPUscheduling.arrat));
                
                ArrayList<Integer> burstsjf = new ArrayList<Integer>(50);
                ArrayList<Integer> completionsjf = new ArrayList<Integer>(50);
                ArrayList<Integer> tsjf = new ArrayList<Integer>(50);
                ArrayList<String> processsjf = new ArrayList<String>();
                
                int clk = 0;
                int min = 0;
                int k, t;
                int minval;
                int index = -1;
                int bef = 0;
                int aft = 0;
                do {
                    k = min;        //maintain prior pro loc
                    minval = 1000;
                    System.out.println("k is"+k);
                    System.out.println("mk is"+min);
                    min = 0;
                       for(int j = 0;j<burst.size();j++){
                           if(clk >= arrival.get(j)&& burst.get(j)!=0){
                               if(minval> burst.get(j)){
                                   minval = burst.get(j);
                                   min = j;
                               }
                           }
                       }
                       if(clk==0||k!=min){
                           //if(burst.get(min)!=0){
                               burst.set(min, burst.get(min)-1);
                                burstsjf.add(1);
                                index++;
                                processsjf.add(process.get(min));
                                System.out.println("in if sjf");
                                bef = burst.get(min)+1;
                                aft = burst.get(min);    
                           //} 
                       }
                       else{
                           burst.set(k, burst.get(k)-1);
                           burstsjf.set(index, burstsjf.get(index)+1);
                           min = k;
                           System.out.println("in else sjf");
                           bef = burst.get(min)+1;
                           aft = burst.get(min);
                       }
                        System.out.println("m is"+min);        
                     clk++;
                     if(aft==0&&bef!=0){
                                    completionsjf.add(clk);
                           } 
                    
                }while(clk<totalBurst);
                
                for(Integer n1 : completionsjf){
              System.out.println("completion:"+n1);  
                }
                for(Integer n1 : burstsjf){
              System.out.println("burstsjf:"+n1);   
                }
                for(String n1 : processsjf){
              System.out.println("process:"+n1);   
                }
              int sum = 0;
                g.drawString(Integer.toString(sum),xCoor, 30);
                for(int i =0; i<burstsjf.size(); i++){
                    if(burstsjf.get(i)!=0){
                        g.drawString(processsjf.get(i), xCoor+1, 15);
                        g.drawLine((burstsjf.get(i)*400/totalBurst) + xCoor, 0,(burstsjf.get(i)*400/totalBurst) + xCoor , 20);
                        
                        sum+=burstsjf.get(i);
                        g.drawString(Integer.toString(sum), (burstsjf.get(i)*400/totalBurst) + xCoor, 30);
                        xCoor = xCoor + burstsjf.get(i)*400/totalBurst;
                    }
                }
                    String ts;
                    min=0;
                      for(k=0; k<process.size(); k++){
                    min=k;
                    for(int l=(k+1);l<process.size()-1;l++){
                        System.out.println(Integer.parseInt((process.get(l)).substring(1)));
                        if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                min = l;
                    }
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                    t = completionsjf.get(min);
                    completionsjf.set(min, completionsjf.get(k));
                    completionsjf.set(k, t);
                    
                    t = bursts.get(min);
                    bursts.set(min, bursts.get(k));
                    bursts.set(k, t);
                    
                    t = arrival.get(min);
                    arrival.set(min, arrival.get(k));
                    arrival.set(k, t);
                }
                      String subs1 = "";
                      String subs2 = "";
                      for(int i=0; i<process.size(); i++){
                          turnAround.add(completionsjf.get(i)-arrival.get(i));
                          waiting.add(turnAround.get(i)-bursts.get(i));
                          subs1 = subs1.concat(process.get(i)+"="+waiting.get(i)+",");
                          subs2 = subs2.concat(process.get(i)+"="+turnAround.get(i)+",");
                      }
                      double avWt=0.0, avTat=0.0;
                      for(int i=0; i<waiting.size(); i++){
                          avWt = avWt+waiting.get(i);
                          avTat = avTat+turnAround.get(i);
                      }
                      subs1 = subs1.concat("           Average Waiting Time:   "+avWt/waiting.size());
                      subs2 = subs2.concat("           Average Turn Around Time:"+avTat/waiting.size());
                      
                CPUscheduling.tf6cb3.setText(subs1);
                CPUscheduling.tf7cb3.setText(subs2);
            }
           if(sel[3]==1){                           //non preemptive sjf
                burst = new ArrayList<Integer>(50);
                arrival = new ArrayList<Integer>(50);
                waiting = new ArrayList<Integer>(50);
                turnAround = new ArrayList<Integer>(50);
                process = new ArrayList<String>();
                
                int clock = 0;
                int c=0;                //iterator for non preemptive sjf array list
                xCoor = 0;
                int start = 0;
                int totalBurst = 0;     //calculate total burst time
                int temp = 0;
                do{
                    int index = 0;                  //of the initial sequence
                    for(Integer j : CPUscheduling.arrat){
                                                       //adding all processes at a particular arrival time denoted by clock
                        if(clock==j.intValue()){      //to add the corresponding details of processes in ascending order of their arrival times
                            burst.add(CPUscheduling.arrbt.get(index));
                            process.add("P"+Integer.toString(index+1));
                            arrival.add(clock);
                            System.out.println("burst"+burst.get(c));
                            System.out.println("process:"+process.get(c));
                            System.out.println("arrival"+arrival.get(c));
                            totalBurst+= burst.get(c);
                            c++;
                        } 
                        index++;
                    }
                    
                    int t;
                    String ts;
                    int min;
                    int cl = 0;
                    
                      for(int k=start; k<burst.size(); k++){
                       
                    min=k;
                    for(int l=(k+1);l<burst.size();l++){
                        //System.out.println(Integer.parseInt((process.get(l)).substring(1)));
                        if(burst.get(l) < burst.get(min)){
                                min = l;
                        }
                    
                        else
                            if(burst.get(l)==burst.get(min)){
                                if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                    min = l;
                            }
                    }
                    t = burst.get(min);
                    burst.set(min, burst.get(k));
                    burst.set(k, t);
                    
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                    if(clock>=temp){
                        start++;
                        System.out.println("temp in if:"+temp);
                        temp = burst.get(k);
                    }
                        
                    System.out.println("START:"+start);
                    System.out.println("clo:"+clock);
                    System.out.println("temp:"+temp);
                }
                    
                    clock++;
                }while(clock<=Collections.max(CPUscheduling.arrat));
                                                                     //to check
                System.out.println("sum"+totalBurst);
                System.out.println("bs"+burst.size());
                System.out.print("xc"+xCoor);
                                                           //for gantt chart
                for(int i =0; i<burst.size(); i++){
                    g.drawLine((burst.get(i)*400/totalBurst) + xCoor, 0,(burst.get(i)*400/totalBurst) + xCoor , 20);
                    xCoor = xCoor + burst.get(i)*400/totalBurst;
                    System.out.println("coord"+xCoor);
                        
                }
                xCoor = 0;
                int sum = 0;
                g.drawString(Integer.toString(sum),xCoor, 30);
                for(int i =0; i<burst.size(); i++){
                    g.drawString(process.get(i), xCoor+1, 15);
                    sum+=burst.get(i);
                    g.drawString(Integer.toString(sum), (burst.get(i)*400/totalBurst) + xCoor, 30);
                    
                    xCoor = xCoor + burst.get(i)*400/totalBurst;
                    turnAround.add(sum-arrival.get(i));
                    waiting.add(turnAround.get(i)-burst.get(i));
                    System.out.println("turnAround:"+turnAround.get(i));
                    System.out.println("waiting:"+waiting.get(i));
                }
                
                //Systematically arranging the processes to print
                    int t;
                    String ts;
                    int min;
                    for(int k=0; k<process.size(); k++){
                    min=k;
                    for(int l=(k+1);l<process.size();l++){
                        System.out.println(Integer.parseInt((process.get(l)).substring(1)));
                        if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                min = l;
                    }
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                    t = waiting.get(min);
                    waiting.set(min, waiting.get(k));
                    waiting.set(k, t);
                    
                    t = turnAround.get(min);
                    turnAround.set(min, turnAround.get(k));
                    turnAround.set(k, t);
                }
                    //concating info for final textfield output
                      System.out.println(waiting.get(1));
                      String subs1 = "";
                      String subs2 = "";
                      for( int i=0; i<process.size(); i++){
                          subs1 = subs1.concat(process.get(i)+"="+waiting.get(i)+",");
                          subs2 = subs2.concat(process.get(i)+"="+turnAround.get(i)+",");
                      }
                      
                      double avWt=0.0, avTat=0.0;
                      for(int i=0; i<waiting.size(); i++){
                          avWt = avWt+waiting.get(i);
                          avTat = avTat+turnAround.get(i);
                      }
                      subs1 = subs1.concat("           Average Waiting Time:   "+avWt/waiting.size());
                      subs2 = subs2.concat("           Average Turn Around Time:"+avTat/waiting.size());
                CPUscheduling.tf6cb4.setText(subs1);
                CPUscheduling.tf7cb4.setText(subs2);
            }
           if(sel[4]==1){                       //preemptive priority
                
                int clock = 0;
                int c=0;                
                xCoor = 0;
                burst = new ArrayList<Integer>(50);
                arrival = new ArrayList<Integer>(50);
                waiting = new ArrayList<Integer>(50);
                turnAround = new ArrayList<Integer>(50);
                ArrayList <Integer> priority = new ArrayList<Integer>(50);
                ArrayList <Integer> burstp = new ArrayList<Integer>(50);
                process = new ArrayList<String>();
                int totalBurst = 0;     //calculate total burst time
                do{
                    int index = 0;                  //of the initial sequence
                    for(Integer j : CPUscheduling.arrat){
                                                       //adding all processes at a particular arrival time denoted by clock
                        if(clock==j.intValue()){      //to add the corresponding details of processes in ascending order of their arrival times
                            burst.add(CPUscheduling.arrbt.get(index));
                            burstp.add(CPUscheduling.arrbt.get(index));
                            process.add("P"+Integer.toString(index+1));
                            arrival.add(clock);
                            priority.add(CPUscheduling.arrpr.get(index));
                            System.out.println("burst"+burst.get(c));
                            System.out.println("process:"+process.get(c));
                            System.out.println("arrival"+arrival.get(c));
                            totalBurst+= burst.get(c);
                            c++;
                        } 
                        index++;
                    }clock++;
                }while(clock<=Collections.max(CPUscheduling.arrat));
                
                ArrayList<Integer> burstpri = new ArrayList<Integer>(50);
                ArrayList<Integer> completionpri = new ArrayList<Integer>(50);
                ArrayList<Integer> tpri = new ArrayList<Integer>(50);
                ArrayList<String> processpri = new ArrayList<String>();
                int clk = 0;
                int min = 0;
                int k, t;
                int minval;
                int index = -1;
                int aft, bef;
                do {
                    k = min;        //maintain prior pro loc
                    minval = 1000;
                    System.out.println("k is"+k);
                    System.out.println("mk is"+min);
                    //for(int i= 0; i<burst.size();i++)
                       min = 0;
                       for(int j = 0;j<priority.size();j++){
                           if(clk >= arrival.get(j)&& burst.get(j)!=0){
                               if(minval> priority.get(j)){
                                   minval = priority.get(j);
                                   min = j;
                               }
                           }
                       }
                         //get least burst
                       if(clk==0||k!=min){
                           //if(burst.get(min)!=0){
                               burst.set(min, burst.get(min)-1);
                                burstpri.add(1);
                                index++;
                                processpri.add(process.get(min));
                                System.out.println("in if priority");
                                bef = burst.get(min)+1;
                                aft = burst.get(min);
                           //} 
                       }
                       else{
                           burst.set(k, burst.get(k)-1);
                           burstpri.set(index, burstpri.get(index)+1);
                           min = k;
                           System.out.println("in else priority");
                           bef = burst.get(min)+1;
                           aft = burst.get(min);
                       }
                         System.out.println("m is"+min);         
                    clk++;
                    if(aft==0&&bef!=0){
                             completionpri.add(clk);
                           } 
                }while(clk<totalBurst);
                
                for(Integer n1 : burstpri){
                System.out.print("burstpri:"+n1);   
                }
                for(String n1 : processpri){
                    System.out.print("process:"+n1);   
                }
                int sum = 0;
                g.drawString(Integer.toString(sum),xCoor, 30);
                for(int i =0; i<burstpri.size(); i++){
                    if(burstpri.get(i)!=0){
                        g.drawString(processpri.get(i), xCoor+1, 15);
                        g.drawLine((burstpri.get(i)*400/totalBurst) + xCoor, 0,(burstpri.get(i)*400/totalBurst) + xCoor , 20);
                        
                        System.out.println("coord"+xCoor);
                        sum+=burstpri.get(i);
                        g.drawString(Integer.toString(sum), (burstpri.get(i)*400/totalBurst) + xCoor, 30);
                        xCoor = xCoor + burstpri.get(i)*400/totalBurst;
                    }
                }
                String ts;
                    min=0;
                      for(k=0; k<process.size(); k++){
                    min=k;
                    for(int l=(k+1);l<process.size()-1;l++){
                        System.out.println(Integer.parseInt((process.get(l)).substring(1)));
                        if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                min = l;
                    }
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                    t = completionpri.get(min);
                    completionpri.set(min, completionpri.get(k));
                    completionpri.set(k, t);
                    
                    t = burstp.get(min);
                    burstp.set(min, burstp.get(k));
                    burstp.set(k, t);
                    
                    t = arrival.get(min);
                    arrival.set(min, arrival.get(k));
                    arrival.set(k, t);
                    
                }
                      String subs1 = "";
                      String subs2 = "";
                      for(int i=0; i<process.size(); i++){
                          turnAround.add(completionpri.get(i)-arrival.get(i));
                          waiting.add(turnAround.get(i)-burstp.get(i));
                          subs1 = subs1.concat(process.get(i)+"="+waiting.get(i)+",");
                          subs2 = subs2.concat(process.get(i)+"="+turnAround.get(i)+",");
                      }
                      double avWt=0.0, avTat=0.0;
                      for(int i=0; i<waiting.size(); i++){
                          avWt = avWt+waiting.get(i);
                          avTat = avTat+turnAround.get(i);
                      }
                      subs1 = subs1.concat("           Average Waiting Time:   "+avWt/waiting.size());
                      subs2 = subs2.concat("           Average Turn Around Time:"+avTat/waiting.size());
                      
                CPUscheduling.tf6cb5.setText(subs1);
                CPUscheduling.tf7cb5.setText(subs2);
            }
           if(sel[5]==1){                               //non preemptive priority
                
                burst = new ArrayList<Integer>(50);
                arrival = new ArrayList<Integer>(50);
                waiting = new ArrayList<Integer>(50);
                turnAround = new ArrayList<Integer>(50);
                process = new ArrayList<String>();
                ArrayList<Integer> priority= new ArrayList<Integer>(50);
                int clock = 0;
                int c=0;                //iterator for non preemptive priority array list
                xCoor = 0;
                int start = 0;
                int totalBurst = 0;     //calculate total burst time
                int temp = 0;
                do{
                    int index = 0;                  //of the initial sequence
                    for(Integer j : CPUscheduling.arrat){
                                                       //adding all processes at a particular arrival time denoted by clock
                        if(clock==j.intValue()){      //to add the corresponding details of processes in ascending order of their arrival times
                            burst.add(CPUscheduling.arrbt.get(index));
                            process.add("P"+Integer.toString(index+1));
                            arrival.add(clock);
                            priority.add(CPUscheduling.arrpr.get(index));
                            System.out.println("burst"+burst.get(c));
                            System.out.println("process:"+process.get(c));
                            System.out.println("arrival"+arrival.get(c));
                            totalBurst+= burst.get(c);
                            c++;
                        } 
                        index++;
                    }
                    
                    for(Integer n1 : CPUscheduling.arrpr){
                    System.out.print("prior:"+n1);   
                }
                    int t;
                    String ts;
                    int min;
                    
                      for(int k=start; k<priority.size(); k++){
                       
                    min=k;
                    for(int l=(k+1);l<priority.size();l++){
                        if(priority.get(l) < priority.get(min)){
                                min = l;
                        }
                    
                        else
                            if(priority.get(l)==priority.get(min)){
                                if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                    min = l;
                            }
                    }System.out.println("Pro :"+process.get(min));
                    t = burst.get(min);
                    burst.set(min, burst.get(k));
                    burst.set(k, t);
                    
                    t = priority.get(min);
                    priority.set(min, priority.get(k));
                    priority.set(k, t);
                    
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                   System.out.println("Pro:"+process.get(k));
                    if(clock>=temp){
                        start++;
                        System.out.println("temp in if:"+temp);
                        temp = burst.get(k);
                        System.out.println("temp in if:"+temp);
                    }
                    System.out.println("START:"+start);
                    System.out.println("clock:"+clock);
                }
                    
                    clock++;
                }while(clock<=Collections.max(CPUscheduling.arrat));
                                                                     //to check
                System.out.println("sum"+totalBurst);
                System.out.println("bs"+burst.size());
                                                           //for gantt chart
                for(int i =0; i<burst.size(); i++){
                    g.drawLine((burst.get(i)*400/totalBurst) + xCoor, 0,(burst.get(i)*400/totalBurst) + xCoor , 20);
                    xCoor = xCoor + burst.get(i)*400/totalBurst;   
                }
                xCoor = 0;
                int sum = 0;
                g.drawString(Integer.toString(sum),xCoor, 30);
                for(int i =0; i<burst.size(); i++){
                    g.drawString(process.get(i), xCoor+1, 15);
                    sum+=burst.get(i);
                    g.drawString(Integer.toString(sum), (burst.get(i)*400/totalBurst) + xCoor, 30);
                    
                    xCoor = xCoor + burst.get(i)*400/totalBurst;
                    turnAround.add(sum-arrival.get(i));
                    waiting.add(turnAround.get(i)-burst.get(i));
                    System.out.println("turnAround:"+turnAround.get(i));
                    System.out.println("waiting:"+waiting.get(i));
                }
                
                //Systematically arranging the processes to print
                    int t;
                    String ts;
                    int min;
                    for(int k=0; k<process.size(); k++){
                    min=k;
                    for(int l=(k+1);l<process.size();l++){
                        System.out.println(Integer.parseInt((process.get(l)).substring(1)));
                        if(Integer.parseInt((process.get(l)).substring(1)) < Integer.parseInt((process.get(min)).substring(1)))
                                min = l;
                    }
                    ts = process.get(min);
                    process.set(min, process.get(k));
                    process.set(k, ts);
                    
                    t = waiting.get(min);
                    waiting.set(min, waiting.get(k));
                    waiting.set(k, t);
                    
                    t = turnAround.get(min);
                    turnAround.set(min, turnAround.get(k));
                    turnAround.set(k, t);
                }
                    //concating info for final textfield output
                      System.out.println(waiting.get(1));
                      String subs1 = "";
                      String subs2 = "";
                      for( int i=0; i<process.size(); i++){
                          subs1 = subs1.concat(process.get(i)+"="+waiting.get(i)+",");
                          subs2 = subs2.concat(process.get(i)+"="+turnAround.get(i)+",");
                      }
                      
                      double avWt=0.0, avTat=0.0;
                      for(int i=0; i<waiting.size(); i++){
                          avWt = avWt+waiting.get(i);
                          avTat = avTat+turnAround.get(i);
                      }
                      subs1 = subs1.concat("           Average Waiting Time:   "+avWt/waiting.size());
                      subs2 = subs2.concat("           Average Turn Around Time:"+avTat/waiting.size());
                CPUscheduling.tf6cb6.setText(subs1);
                CPUscheduling.tf7cb6.setText(subs2);
            }
     }
    }
/*
Employee Details
    id
    first name
    last name
    age
    mobile
    email
    date of birth
    joining date
    salary
    department
    City
    Pincode


*/
package employeemanagement;
/*

import java.io.*;
class General 
{
    public static void main(String[] args) throws IOException
    {
        int id[]=new int[10];
        int salary[] = new int[10];
        int age[] = new int[10];
        int n,i,j,ea=0;
        int totalSal = 0;
        String name[]=new String[10];
        String City[]=new String[10];
        String branch[]=new String[10];
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
        do
        {
            System.out.println("\n 1. Add the Employee ");
            System.out.println("\n 2. Show All Employee ");
            System.out.println("\n 3. Delete the Employee ");
            System.out.println("\n 4. Modify Data of Employee ");
            System.out.println("\n 5. Calculate Avrage Salary");
            System.out.println("\n 6. Exit");
            System.out.println("\n    Enter your Choice : ");
            n = Integer.parseInt(b.readLine());
            switch(n)
            {
                case 1:
                {
                    System.out.println("\nHow many Employee Do you want to Add ? -: ");
                    ea =Integer.parseInt(b.readLine());
                    for(i=0;i<ea;i++)
                    {
                           System.out.println("\n Enter ID of Employee : ");
                           id[i]=Integer.parseInt(b.readLine());
                           System.out.println("\n Enter Name of Employee : ");
                           name[i]=b.readLine();
                           System.out.println("\n Enter City of Employee : ");
                           City[i]=b.readLine();
                           System.out.println("\n Enter Age of Employee : ");
                           age[i]=Integer.parseInt(b.readLine());
                           System.out.println("\n Enter Salary of Employee : ");
                           salary[i]=Integer.parseInt(b.readLine());
                           System.out.println("\n Enter branch of Employee : ");
                           branch[i]=b.readLine();
                           System.out.println("\n This Employee Added at "+i+" Location");
                           System.out.println("\n--------------------------------------------------------------");
                    }
                }
                break;
                case 2:
                {
                    System.out.println("\n__________________CASE 2_____________________");
                    System.out.println("\nID \t Name \t City \t Age \t Salary\tBranch\tEmpNo");
                    for(i=0;i<ea;i++)
                    {
                        System.out.println(id[i]+"\t"+name[i]+"\t"+City[i]+"\t"+age[i]+"\t"+salary[i]+"\t"+branch[i]+"\t"+i);
                    }
                    System.out.println("\n_____________________________________________________________");
                }
                break;
                case 3:
                {
                    System.out.println("\n__________________CASE 3_____________________");
                    for(i=0;i<ea;i++)
                    {
                        System.out.println(id[i]+"\t"+name[i]+"\t"+City[i]+"\t"+age[i]+"\t"+salary[i]+"\t"+branch[i]+"\t"+i);
                    }                    
                    System.out.println("Enter Employee No to Delete : ");
                    int en=Integer.parseInt(b.readLine());
                    for(i=0;i<ea;i++)
                    {
                        if(en!=i)
                            continue;
                        else
                        {
                            for(i=en;i<ea;i++)              //own logic to delete
                            {
                                id[i]=id[i+1];
                                name[i]=name[i+1];
                                City[i]=City[i+1];
                                age[i]=age[i+1];
                                salary[i]=salary[i+1];
                                branch[i]=branch[i+1];
                            }
                        }
                    }
                    ea=ea-1;
                    System.out.println("\n_____________________________________________________________");
                }
                break;
                case 4:
                {
                    int me;
                    for(i=0;i<ea;i++)
                    {
                        System.out.println(id[i]+"\t"+name[i]+"\t"+City[i]+"\t"+age[i]+"\t"+salary[i]+"\t"+branch[i]+"\t"+i);
                    }   
                    System.out.println("Enter Employee no is to be Modify : ");
                    me=Integer.parseInt(b.readLine());
                    for(i=0;i<ea;i++)
                    {
                        if(me==i)
                        {
                            System.out.println("Which Feild you  want to Modify  : ");
                            String mef=b.readLine();   //mef= modiify emp field
                            if(null == mef)
                                System.out.println("Sorry...No such Feild");
                            else switch (mef) 
                            {
                                case "id":
                                case "ID":
                                    System.out.println("Enter Proper ID : ");
                                    id[i]=Integer.parseInt(b.readLine());
                                    break;
                                case "name":
                                case "Name":
                                case "NAME":
                                    System.out.println("Enter Employee Proper Name : ");
                                    name[i]=b.readLine();
                                    break;
                                case "City":
                                case "city":
                                case "CITY":
                                    System.out.println("Enter Employee Proper City : ");
                                    City[i]=b.readLine();
                                    break;
                                case "age":
                                case "Age":
                                case "AGE":
                                    System.out.println("Enter Employee Proper Age : ");
                                    age[i]=Integer.parseInt(b.readLine());
                                    break;
                                case "salary":
                                case "Salary":
                                case "SALARY":
                                    System.out.println("Enter Employee Proper salary : ");
                                    salary[i]=Integer.parseInt(b.readLine());
                                    break;
                                case "branch":
                                case "Branch":
                                case "BRANCH":
                                    System.out.println("Enter Employee Proper branch : ");
                                    branch[i]=b.readLine();
                                    break;
                                default:
                                    System.out.println("Sorry...No such Feild");
                                    break;
                            }
                        }
                    }
                    System.out.println("\n_______Modified List_________");
                    for(i=0;i<ea;i++)
                    {
                        System.out.println(id[i]+"\t"+name[i]+"\t"+City[i]+"\t"+age[i]+"\t"+salary[i]+"\t"+branch[i]+"\t"+i);
                    }
                }
                break;
                case 5 : 
                    for(int avg = 0 ;avg < ea ;avg++)
                    {
                        totalSal += salary[avg];
                    }
                    double avgSal = (double)totalSal/ea;
                    System.out.println("Total Avrage Salary of an Employee's : "+avgSal);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n Invalid Choice Please Enter Correct Choice ");
                    break;

            }
        }while(n!=6);
    }
}
*/


public class General
{
   
}

















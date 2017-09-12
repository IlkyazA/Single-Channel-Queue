
package queue; 
import java.io.*;

	class queue
	
	{ 
		public static void main(String a[]) throws Exception, IOException
	{	
	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	float avgser=0,queueavg=0,serviceavg=0;
	int i,no,count=0,icount=0,j;
	System.out.println("Please enter the number of customers");
	no=Integer.parseInt(br.readLine());
	int starttime[]=new int[no];
	int endtime[]=new int[no];
	int arrival[]={1,2,3,4,5,6,7,8};
	int arrivalrda[]=new int[arrival.length];
	float cumulativearrival[]=new float[arrival.length];
	float arrivalprob[]={0.125f,0.125f,0.125f,0.125f,0.125f,0.125f,0.125f,0.125f};
	int timesincelastarrival[]=new int[no];
	int service[]={1,2,3,4,5,6};
	float serviceprob[]={0.10f,0.20f,0.30f,0.25f,0.10f,0.05f};
	float cumulativeservice[]=new float[service.length];
	int servicerda[]=new int[service.length];
	int servicetime[]=new int[no];
	int randomarrival[]=new int[no];
	int randomservice[]=new int[no];
	int timearrival[]=new int[no];
	int temp1queue[]=new int[no];
	int temp1service[]=new int[no];
	int idletime[]=new int[no];
	for(i=0;i<arrival.length;i++)
		{	if(i==0) //ilk müþteri
			{	cumulativearrival[i]=arrivalprob[i];
				arrivalrda[i]=(int)(cumulativearrival[i]*100);
			}
// kendisi ve bir önceki müþteriyi al
			else
			{	cumulativearrival[i]=cumulativearrival[i-1]+arrivalprob[i];
				arrivalrda[i]=(int)(cumulativearrival[i]*100);
			}
		}
	for(i=0;i<service.length;i++)
		{	if(i==0) //ilk servis
			{	cumulativeservice[i]=serviceprob[i];
				servicerda[i]=(int)(cumulativeservice[i]*100);
			}
			else
			{	cumulativeservice[i]=cumulativeservice[i-1]+serviceprob[i];
				servicerda[i]=(int)(cumulativeservice[i]*100);
			}
		}
	randomservice[0]=(int)(Math.random()*100d);
	for(i=1;i<no;i++)
	{	randomarrival[i]=(int)(Math.random()*100d);
		randomservice[i]=(int)(Math.random()*100d);
	}
	for(i=1;i<no;i++)
	{	for(j=0;j<arrivalrda.length;j++)
		{	if(randomarrival[i]==0)
		{	timesincelastarrival[i]=arrival[arrival.length-1];
			break;
		}
		if(randomarrival[i]<=arrivalrda[j])
			{	timesincelastarrival[i]=arrival[j];
			break;
			}
		}
		timearrival[i]=timearrival[i-1]+timesincelastarrival[i];
	}
	for(i=0;i<no;i++)
		{	for(j=0;j<servicerda.length;j++)
			{	if(randomservice[i]==0)
			{	servicetime[i]=service[service.length-1];
			break;
		}
			if(randomservice[i]<=servicerda[j])
				{	servicetime[i]=service[j];
					break;
				}
			}
	}
	for(i=0;i<no;i++)
		{	if(i==0)
			{	starttime[i]=0;
				endtime[i]=starttime[i]+servicetime[i];
				temp1service[i]=servicetime[i];
			}
			else
			{	if(endtime[i-1]>=timearrival[i])
				{	temp1queue[i]=endtime[i-1]-timearrival[i];
					starttime[i]=endtime[i-1];
					endtime[i]=starttime[i]+servicetime[i];
					temp1service[i]=servicetime[i]+temp1queue[i];
				}
			else
			{	idletime[i]=timearrival[i]-endtime[i-1];
				starttime[i]=timearrival[i];
				endtime[i]=starttime[i]+servicetime[i];
				temp1service[i]=servicetime[i];
			}
			}
		}
	for(i=0;i<no;i++)
	{	avgser=avgser+servicetime[i];
		queueavg=queueavg+temp1queue[i];
		serviceavg=serviceavg+temp1service[i];
		if(temp1queue[i]>0)
				count++;
		if(idletime[i]>0)
				icount++;
	}
	System.out.println("# \tTime Since\tArrival Time\tTime Service\tService Time \tTime Service\tIdle Time");
	System.out.println("\tLast Arrival\t\t\tBegins\t\t(Duration)\tEnds\t\tOf Service");
	for(i=0;i<no;i++)		
	{
		System.out.println((i+1)+"\t"+timesincelastarrival[i]+"\t\t"
		+timearrival[i]+"\t\t"+starttime[i]+"\t\t"+servicetime[i]+"\t\t"+endtime[i]+"\t\t"
		+idletime[i]);

	}

	System.out.println("Customer Queue Simulation Results :");
	System.out.println("Avg Service Time :"+(avgser/no));
	System.out.println("Avg Time Spent by Customer:"+(serviceavg/no));
	if(count>0)
	System.out.println("Avg Waiting Time for a Customer :"+(queueavg/count));
	System.out.println("Probability of Waiting in the Queue:"+((float)count/no));
	System.out.println("Probability of idle time of the service :"+((float)icount/no));
	}
	}

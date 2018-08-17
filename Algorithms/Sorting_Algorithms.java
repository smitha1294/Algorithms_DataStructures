package sorting;

import java.util.*;

public class sorts {
	   void SelectionSort(int arr[])
	    {
		   for(int i=0;i<arr.length-1;i++) {
			 int  index=i;
			   for(int j=i+1;j<arr.length;j++) 
				  if(arr[j]<arr[index])
					  index=j;
	            int temp = arr[i];
	            arr[i] = arr[index];
	            arr[index] = temp;
		   }
	    }
	   
	   void insertion(int arr[]) {
		   for(int i=0;i<arr.length-1;i++) {
			   for(int j=i+1;j>0;j--) {
				   if(arr[j]<arr[j-1]) {
			            int temp = arr[j];
			            arr[j] = arr[j-1];
			            arr[j-1] = temp;
				   }	}}}
	   
	   void bubble(int arr[]) {
		   for(int i=arr.length-1;i>=0;i--){
			   for(int j=0;j<i;j++) {
				   if(arr[j]>arr[j+1]) {
			            int temp = arr[j];
			            arr[j] = arr[j+1];
			            arr[j+1] = temp;
				   }
			   }
		   }
	   }
	   //merge sort section
	   void merge(int arr[],int l, int r) {
		   if(l<r) {
		   int m=(l+r)/2;
		   merge(arr,l,m);
		   merge(arr,m+1,r);
		   mergeAll(arr,l,r,m);
		   }   
	   }
	   void mergeAll(int arr[],int l, int r, int m) {
		   int n1=m+1-l,n2=r-m;
		   int[] L=new int[n1];
		   int[] R=new int[n2];
		   for(int i=0;i<n1;i++)
			   L[i]=arr[l+i];
		   for(int i=0;i<n2;i++)
			   R[i]=arr[m+1+i];
		   int i=0,j=0,k=l;
		   while(i<n1&&j<n2) {
			   if(L[i]<=R[j]) {
				   arr[k]=L[i];
				   i++;
			   }
			   else {
				   arr[k]=R[j];
				   j++;
			   }
			   k++;
		  }
		  while(i<n1) {
			  arr[k]=L[i];
			  i++;
			  k++;
		  }
		  while(j<n2) {
			  arr[k]=R[j];
			  j++;
			  k++;
		  }
		   }
	   
	   //quick sort
	   void quick(int arr[],int l, int r) {
		   int i=l,j=r,p=arr[r];
		   while(i<=j) {
			   while(arr[i]<p) i++;
			   while(arr[j]>p) j--;
			   if(i<=j) {
		            int temp = arr[j];
		            arr[j] = arr[i];
		            arr[i] = temp;  
		            i++;j--;
			   }
		   }
		   if(j>l) quick(arr,l,j);
		   if(i<r) quick(arr,i,r);
		   }
	   
	   //heap sort
	   void heap(int arr[],int l, int r) {
		   int n= arr.length;
		   for(int i=n/2-1;i>=0;i--)
			   heapify(arr,i,n);
		   for(int i=n-1;i>=0;i--) {
	            int temp = arr[0];
	            arr[0] = arr[i];
	            arr[i] = temp;  
	            heapify(arr,0,i);
		   }
	   }
	   void heapify(int arr[],int s, int e) {
		   int max=s,l=2*s+1,r=2*s+2; // i,2i
		   if(l<e&&arr[l]>arr[max]) max=l;
		   if(r<e&&arr[r]>arr[max]) max=r;
		   if(max!=s) {
	            int temp = arr[0];
	            arr[0] = arr[max];
	            arr[max] = temp;  		   
	            heapify(arr,max,e);
		   }
	   }
	   void bucketSort(int[] arr) {
           int i;
           List<Integer>[] bucket = new List[10];

           for (i = 0; i < bucket.length; i++) {
               bucket[i]=new ArrayList<Integer>();
           }
           for (i = 0; i < arr.length; i++) {
        	   		bucket[arr[i]/10].add(arr[i]);
           }
            for (List<Integer> b:bucket) {
                   Collections.sort(b);
               }
            int k=0;
            for (List<Integer> b:bucket) {
            		for(Integer a:b) {
            			arr[k++]=a;
            		}}}
	   
	   void bucketSortL(int[] arr) {
           int i,j;
           List<List<Integer>> bucket = new ArrayList<List<Integer>>(10);

           for (i = 0; i < 10; i++) {
               bucket.add(new ArrayList<Integer>());
           }
           for (i = 0; i < arr.length; i++) {
        	   		bucket.get(arr[i]/10).add(arr[i]);
           }
            for (List<Integer> b:bucket) {
                   Collections.sort(b);
               }
            int k=0;
            for (List<Integer> b:bucket) {
            		for(j = 0; j < b.size(); j++) {
            			arr[k++]=b.get(j);
            		}}}
	   
	   void radix(int arr[])
	    {           
		int i,d=1;
        List<Integer>[] bucket = new List[10];

        for (i = 0; i < bucket.length; i++) {
            bucket[i]=new ArrayList<Integer>();
        }
   
	while(d<=100) {
        for (int a:arr) {
        		int t=a/d; 
     	   	bucket[t%10].add(a);
        }
     	 int k=0;
         for (int b = 0; b < 10; b++) {
         		for(Integer ia:bucket[b]) 
         			arr[k++]=ia;
         		bucket[b].clear();
         }
        d*=10;
        }
	    }
	    void printArray(int arr[])
	    {
	        int n = arr.length;
	        for (int i=0; i<n; ++i)
	            System.out.print(arr[i]+" ");
	        System.out.println();
	    }
	 
	    // Driver code to test above
	    public static void main(String args[])
	    {
	        sorts ob = new sorts();
	      //  int arr[] = {64,25,12,22,11,7};
	        int arr[] = {643,251,126,127,132,75};
	        ob.bubble(arr);
	        ob.merge(arr,0,arr.length-1);
	        ob.quick(arr,0,arr.length-1);
	        ob.heap(arr,0,arr.length-1);
	        ob.bucketSort(arr);
	        ob.radix(arr);
	        System.out.println("Sorted array");
	        ob.printArray(arr);
	    }
}

#include <iostream>
#include <fstream>
#include <climits>
#include <cstdlib>
#include <vector>
#include <algorithm>
#include "Timer.h"
using namespace std;

int findMid(int input[], int n)
{
	sort(input, input+n);
	return input[n/2];
}

int deterministicSelect(vector<int> input, int start, int end, int k, int g_size)
{
 int g, n=end-start+1, babyMedian;
 int temp[n];
 vector<int> groupsMedian((n+(g_size-1))/g_size), less, equal, greater;
 if (!(k > 0) && !(k <= n))
  return -1;

 // Grouping phase
 for (g=0; g<n/g_size; g++){
	 for (int i=g*g_size; i<(g*g_size)+g_size; i++){
		 temp[i] = input[i];
	 }
	 groupsMedian[g] = findMid(temp+g*g_size, g_size);
 }
 if (g*g_size < n)
 {
	 for (int i=g*g_size; i<(g*g_size)+g_size; i++){
		 temp[i] = input[i];}
	 groupsMedian[g] = findMid(temp+g*g_size, n%g_size);
     g++;
 }

// Baby median phase
 if (g==1)
	 babyMedian = groupsMedian[g-1];
 else
	 babyMedian = deterministicSelect(groupsMedian, 0, g-1, g/2, g_size);

 // Partition phase
 for (int i=0;i<n;i++){
	 if (input[i] < babyMedian){
			 less.push_back(input[i]);
	 }
	 else if (input[i] == babyMedian){
			 equal.push_back(input[i]);
	 }
	 else if (input[i] > babyMedian){
			 greater.push_back(input[i]);
	 }
}
 if (k <= less.size())
	 return deterministicSelect(less, 0, less.size()-1, k, g_size);
 else if (k <= less.size()+equal.size())
	 return babyMedian;
 else
 	 return deterministicSelect(greater, 0, greater.size()-1, k-(less.size()+equal.size()), g_size);

}

int quickSelect(vector<int> input, int start, int end, int k)
{
 int n=end-start+1, randMedianIndex;
 vector<int> less, equal, greater;
 if (!(k > 0) && !(k <= n))
  return -1;

 //random median phase
 srand(time(NULL));
 randMedianIndex = rand() % n;

 // Partition phase
 for (int i=0;i<n;i++){
	 if (input[i] < input[randMedianIndex]){
		 less.push_back(input[i]);
	 }
	 else if (input[i] == input[randMedianIndex]){
		 equal.push_back(input[i]);
	 }
	 else if (input[i] > input[randMedianIndex]){
		 greater.push_back(input[i]);
	 }
}
 if (k <= less.size())
	 return quickSelect(less, 0, less.size()-1, k);
 else if (k <= less.size()+equal.size())
	 return input[randMedianIndex];
 else
 	 return quickSelect(greater, 0, greater.size()-1, k-(less.size()+equal.size()));

}

int main()
{
vector<int> input(50);
Timer t;
double eTimeU3,eTimeU5,eTimeU7,eTimeU9,eTimeUq;

srand(time(NULL));
generate(input.begin(), input.end(), rand);

cout<<"K to find K'th smallest element be:"<<input.size()/2<<"\n";
t.start();
cout << "k'th smallest element in DSelect Group=3: "<< deterministicSelect(input, 0, input.size()-1, input.size()/2, 3)<<"\t";
t.elapsedUserTime(eTimeU3);
cout <<"Running Time of DSelect G=3: "<<eTimeU3<<"\n";
t.start();
cout << "k'th smallest element in DSelect Group=5: "<< deterministicSelect(input, 0, input.size()-1, input.size()/2, 5)<<"\t";
t.elapsedUserTime(eTimeU5);
cout <<"Running Time of DSelect G=5: "<<eTimeU5<<"\n";
t.start();
cout << "k'th smallest element in DSelect Group=7: "<< deterministicSelect(input, 0, input.size()-1, input.size()/2, 7)<<"\t";
t.elapsedUserTime(eTimeU7);
cout <<"Running Time of DSelect G=7: "<<eTimeU7<<"\n";
t.start();
cout << "k'th smallest element in DSelect Group=9: "<< deterministicSelect(input, 0, input.size()-1, input.size()/2, 9)<<"\t";
t.elapsedUserTime(eTimeU9);
cout <<"Running Time of DSelect G=9: "<<eTimeU9<<"\n";
t.start();
cout << "k'th smallest element in QuickSelect: "<< quickSelect(input, 0, input.size()-1, input.size()/2)<<"\t";
t.elapsedUserTime(eTimeUq);
cout <<"Running Time of QuickSelect: "<<eTimeUq<<"\n";
return 0;
}

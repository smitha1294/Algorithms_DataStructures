//============================================================================
// Name        : LCS.cpp
// Author      : smitha
// Version     :
// Copyright   : Your copyright notice
// Description : LCS in C++, Ansi-style
//============================================================================

#include <iostream>
#include <climits>
#include <cstdlib>
#include <algorithm>
#include <vector>
#include "Timer.h"

using namespace std;

int LCSlinearSpace (vector<char> x, vector<char> y){
	int xl = x.size();
	int yl = y.size();
	int i,j;
	//int T[2][yl+1];
	vector< vector<int> >T(2, vector<int>(yl+1) );
	bool XIndex;
	for (i=0; i<=xl; i++) {
	        XIndex = i&1;
	        for (j=0; j<=yl; j++)
	        {
	            if (i == 0 || j == 0)
	                T[XIndex][j] = 0;
	            else if (x.at(i-1) == y.at(j-1))
	                T[XIndex][j] = T[1-XIndex][j-1] + 1;
	            else
	                T[XIndex][j] = max(T[1-XIndex][j], T[XIndex][j-1]);
	        }
	    }
	    return T[XIndex][yl];
}

int LCSdynamic (vector<char> x, vector<char> y){
	int xl = x.size();
	int yl = y.size();
	int i,j;
	//int T[xl+1][yl+1];
	vector< vector<int> >T(xl+1, vector<int>(yl+1) );
	for(i=0;i<=xl;i++){
		for(j=0;j<=yl;j++){
			if(i == 0 || j == 0)
				T[i][j]=0;
			else if (x.at(i-1) == y.at(j-1))
				T[i][j]=T[i-1][j-1]+1;
			else
				T[i][j]=max(T[i-1][j], T[i][j-1]);
		}
	}
	return T[xl][yl];
}
char letters() {

	return rand()%26+65;
}
int main() {
    static const int inputSize[] = {
    		5,10,20,50,100,250,500,750,1000,1500,2000,3000,5000,7500,
    		                10000,15000,20000,25000,30000,40000};
    vector<int> input (inputSize, inputSize + sizeof(inputSize) / sizeof(inputSize[0]) );
    for (int a = 0; a < input.size(); a++) {
	vector<char> X(input[a]);
	vector<char> Y(input[a]);
	//cout<<"String Length "<<input[a]<<"\n";
	srand(time(NULL));
	generate(X.begin(), X.end(), letters);
	generate(Y.begin(), Y.end(), letters);
    Timer t;
    double eTimeU1,eTimeU2;
    t.start();
    LCSdynamic(X, Y);
   // cout <<"Result of LCS: "<<LCSdynamic(X, Y)<<"\n";
    t.elapsedUserTime(eTimeU1);
    t.start();
    int s = LCSlinearSpace(X, Y);
   // cout <<"Result of space optimized LCS: "<<LCSlinearSpace(X, Y)<<"\n";
    t.elapsedUserTime(eTimeU2);
    cout <<input[a]<<", "<<eTimeU1<<","<<eTimeU2<<","<<s<<"\n";
    }
	return 0;
}

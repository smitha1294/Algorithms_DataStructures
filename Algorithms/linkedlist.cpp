//============================================================================
// Name        : linkedlist.cpp
// Author      : smitha
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
using namespace std;
class MergeLinkedList {
public:
	struct Node
	{
		int data;
		struct Node *next;
	};
	void MoveNode(struct Node** destRef, struct Node** sourceRef)
		{
			struct Node* newNode=*sourceRef;
			assert(newNode!=NULL);
			*sourceRef=newNode->next;
			newNode->next=*destRef;
			*destRef=newNode;
		}
	struct Node* SortedMerge(struct Node *a,struct Node *b)
	{
		struct Node dummy;
		struct Node* tail=&dummy;
		dummy.next=NULL;
		while(1)
		{
			if(a==NULL)
			{
				tail->next=b;
				break;
			}
			if(b==NULL)
			{
				tail->next=a;
			}
			if(a->data<=b->data)
				MoveNode(&(tail->next),&a);
			else
				MoveNode(&(tail->next),&b);
			tail=tail->next;
		}
		return dummy.next;
	}
	 void printList(struct Node *node)
	{
		while(node!=NULL)
		{
			printf("%d",node->data);
			node=node->next;
		}
	}
	void push(struct Node** head_ref, int new_data)
	{
	    /* allocate node */
	    struct Node* new_node =
	        (struct Node*) malloc(sizeof(struct Node));

	    /* put in the data  */
	    new_node->data  = new_data;

	    /* link the old list off the new node */
	    new_node->next = (*head_ref);

	    /* move the head to point to the new node */
	    (*head_ref)    = new_node;
	}

};
	int main()
	{
	    /* Start with the empty list */
		MergeLinkedList m;
		MergeLinkedList::Node* res = NULL;
		MergeLinkedList::Node* a = NULL;
		MergeLinkedList::Node* b = NULL;

	    /* Let us create two sorted linked lists to test
	      the functions
	       Created lists, a: 5->10->15,  b: 2->3->20 */
	    m.push(&a, 15);
	    m.push(&a, 10);
	    m.push(&a, 5);

	    m.push(&b, 20);
	    m.push(&b, 3);
	    m.push(&b, 2);

	    /* Remove duplicates from linked list */
	    res = m.SortedMerge(a, b);

	    printf("Merged Linked List is: \n");
	    m.printList(res);

	    return 0;
	}

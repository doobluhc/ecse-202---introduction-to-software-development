/*
 ============================================================================
 Name        : database.c
 Author      : Cheng Chen 260775674
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXLEN 200 //defines the maximum of the arrays
struct StudentRecord{
	char first[MAXLEN];
	char last[MAXLEN];
	int ID;
	int marks;
	struct StudentRecord *left;
	struct StudentRecord *right;
};
//this funtion adds nodes to the btree
void addNode(struct StudentRecord** root,struct StudentRecord* input,int type )
{
	if((*root)==NULL)//if the btree is empty, add new node
	{
		(*root)=(struct StudentRecord*)malloc(sizeof(struct StudentRecord));
		(*root)->ID=input->ID;
		(*root)->marks=input->marks;
		strcpy((*root)->first,input->first);
		strcpy((*root)->last,input->last);
		(*root)->left=NULL;
		(*root)->right=NULL;
	}
	else
	{
		if(type==1)//checks if the type is ID or last name
		{
			if(input->ID<(**root).ID)
				addNode(&((*root)->left),input,type);
			else
				addNode(&((*root)->right),input,type);
		}
		else
		{
			if(strcasecmp(input->last,(**root).last)<0)
				addNode(&((*root)->left),input,type);
			else
				addNode(&((*root)->right),input,type);
		}
	}

}

void traverseName(struct StudentRecord* root)
{
	if(root->left != NULL)
		traverseName(root->left);//traverse the tree recursively
	printf("%-10s %-10s\t%-6d\t%d\n",root->first,root->last,root->ID,root->marks);
	if(root->right !=NULL)
		traverseName(root->right);
}
void traverseID(struct StudentRecord* root)
{
	if(root->left!=NULL)
		traverseID(root->left);//traverse the tree recursively
	printf("%-10s %-10s\t%-6d\t%d\n",root->first,root->last,root->ID,root->marks);
	if(root->right!=NULL)
		traverseID(root->right);
}
void searchName(struct StudentRecord* root,char* name,int* foundName)
{
	if(root->left!=NULL)
	{
		searchName(root->left,name,foundName);
	}
	if(strcasecmp(root->last,name)==0||strcasecmp(root->first,name)==0)//compare the name in the tree and the name to be searched
	{
		printf("Student Name: %s %s\n", root->first, root->last);
		printf("Student ID: %d\n", root->ID);
		printf("Total Grade : %d\n\n", root->marks);
		*foundName=1;
	}

	if(root->right!=NULL)
	{
		searchName(root->right,name,foundName);
	}
}
void searchID(struct StudentRecord* root, int ID,int* foundID )
{
	if(root->left!=NULL)
	{
		searchID(root->left,ID,foundID);
	}
	if(ID==root->ID)//compare the IDs, if equal, print the info
	{
		printf("Student Name: %s %s\n", root->first, root->last);
		printf("Student ID: %d\n", root->ID);
		printf("Total Grade : %d\n\n", root->marks);
		*foundID=1;
	}
	if(root->right!=NULL)
	{
		searchID(root->right,ID,foundID);
	}
}
int main(int argc, char* argv[]) {

	struct StudentRecord data, *rootName, *rootID;
	FILE *NamesIDs;
	FILE *Marks;
	//reading the files
	if ((NamesIDs = fopen(argv[1],"r")) == NULL) {
		printf("Can’t open %s\n",argv[1]);
		return -1;
	}
	if ((Marks = fopen(argv[2],"r")) == NULL) {
		printf("Can’t open %s\n",argv[2]);
		return -2;
	}
	rootName=NULL;
	rootID=NULL;
	int numrecords=0;
	//copy the files into the btree nodes
	while (fscanf(NamesIDs,
			"%s%s%d",
			&(data.first[0]),
			&(data.last[0]),
			&(data.ID)) != EOF) {
		fscanf(Marks,"%d",&(data.marks));
		numrecords++;
		addNode(&rootName,&data,2);//calls the addNode function to insert data
		if (rootName==NULL) {
			printf("Error creating name B-Tree, aborted.\n\n");
			return -3;
		}
		addNode(&rootID,&data,1);
		if (rootID==NULL) {
			printf("Error creating ID B-Tree, aborted.\n\n");
			return -4;
		}
	}
	fclose(NamesIDs);
	fclose(Marks);
	char command[MAXLEN];

	do{
		int foundID=0;
		int foundName=0;
		printf("sdb: ");
		scanf("%s",command);
		if(strcasecmp(command,"FN")==0)//search for name
		{
			char name[MAXLEN];
			printf("Enter the name you want to search: ");
			scanf("%s",name);
			searchName(rootName,name,&foundName);
			if(foundName==0)
				printf("There is no student with that name.\n\n");
		}
		else if(strcasecmp(command,"LN")==0)//sort by last name
		{
			printf("\nStudent Record Database sorted by Last Name\n\n");
			traverseName(rootName);
			printf("\n\n");
		}
		else if(strcasecmp(command,"LI")==0)//sort by IDs
		{
			printf("\nStudent Record Database sorted by ID\n\n");
			traverseID(rootID);
			printf("\n\n");
		}
		else if(strcasecmp(command,"HELP")==0)//print the list
		{
			printf("LN\tList all the records in the database ordered by last name.\n"
					"LI\tList all the records in the database ordered by student ID.\n"
					"FN\tPrompts for a name and lists the record of the student with the corresponding name.\n"
					"FI\tPrompts for a name and lists the record of the student with the Corresponding ID.\n"
					"HELP\tPrints this list\n"
					"?\tPrints this list\n"
					"Q\tExits the program.\n");
		}
		else if(strcasecmp(command,"?")==0)//print the list
		{
			printf("LN\tList all the records in the database ordered by last name.\n"
					"LI\tList all the records in the database ordered by student ID.\n"
					"FN\tPrompts for a name and lists the record of the student with the corresponding name.\n"
					"FI\tPrompts for a name and lists the record of the student with the Corresponding ID.\n"
					"HELP\tPrints this list\n"
					"?\tPrints this list\n"
					"Q\tExits the program.\n");
		}
		else if(strcasecmp(command,"Q")==0)// exit the program
		{
			printf("Program terminated...\n\n");
			exit(0);
		}
		else if(strcasecmp(command,"FI")==0)//search for ID
		{
			int ID=0;
			printf("Enter the ID you want to search: ");
			scanf("%d",&ID);
			searchID(rootID,ID,&foundID);
			if(foundID==0)
			{
				printf("There is no student with that ID.\n\n");
			}

		}
		else
			printf("Error, invalid command.\n\n");
	}while(1);
}




/*
 ============================================================================
 Name        : dec2base.c
 Author      : Cheng Chen 260775674
 Version     :
 Copyright   : Your copyright notice
 Description : dec2base
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
//this function reverses the string
void revStr(char *str, int length){
	char* str_rev = (char*)malloc(sizeof(char)*length);
	int i;
	for( i = 0 ; i < length ; i++)
		str_rev[i] = str[length-i-1];
	for(i = 0 ; i < length; i++)
		printf("%c",str_rev[i]);
		printf("\n");

}
//this function switches the base of the number to given base
void dec2base(int input,int base,char *str)
{
	int q = input;
	int i = 0;
	while(q > 0)
	{
		int r = q%base;
		q = q/base;
		//store r as a character
		char rChar;
		if(r < 10)
			rChar = '0'+r;
		else
			rChar = 'A'+(r-10);
		str[i] = rChar;
		i++;
	}
	revStr(str,i);
}

int main(int argc, char* argv[])	//argc = number of arguments, argv = pointers
{

	int num,base;

	//checks if the arguments exceed the limit
	if(argc > 3)
	{
		printf("Wrong number of arguments");
		return(-1);
	}
	//if there is nothing entered for second argument, default base is 2
	if(argc == 2)
	{
		sscanf(argv[1],"%d",&num);
		if(num<0||num>2147483647)
		{
			printf("ERROR: number must be in the range of [0,2147483647]");
			printf("\n");
			return(-1);
		}
		else
		{
			base=2;
		}

	}
	if(argc == 3){
		sscanf(argv[1],"%d",&num);
		sscanf(argv[2],"%d",&base);
		//checks if the number is in the range
		if(num<0||num>2147483647)
		{
			printf("ERROR: number must be in the range of [0,2147483647]");
			printf("\n");
			return(-1);
		}
		//checks if the base is in the range
		if(base<2||base>36)
		{
			printf("Error: base must be in the range [2,36]");
			printf("\n");
			return(-1);
		}
	}
	int q = num;
	int length = 0;
	//if the number is 0, just print 0
	if(q==0)
	{
		printf("the Base-%d form of %d is: 0",base,num);
		printf("\n");
		return 0;
	}
	while(q > 0)
	{
		int r = q%base;
		q = q/base;
		length++;
	}
	char* str = (char*)(malloc(sizeof(char)*length));
	printf("the Base-%d form of %d is: ",base,num);
	dec2base(num,base,str);
	return 0;

}

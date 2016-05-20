#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//A senate group structure, each having a number and a name
struct Senate {
	char group;
	int number;
};

void sortSenates(struct Senate *senates, int low, int high);

int main(int argc, char **argv) {
	int n = argc - 2, i = 0, sum = 0;

	struct Senate *senates;
	senates = malloc(sizeof(struct Senate) * n);
	
	//Initialize the list, give each group a number and a letter
	while (n > 0) {
		senates[i].number = atoi(argv[i + 2]);
		senates[i].group = 97 + i;
		
		sum += senates[i].number;
		
		i++;
		n--;
	}
	
	n = argc - 2;
	i = 0;
	
	//Sort the list according to how many senates there are in each group
	sortSenates(senates, 0, n);
	
	//If there are odd senates, let only one out
	if (sum % 2 != 0) {
		printf("%c ", senates[n - 1].group);
		senates[n - 1].number--;
		sortSenates(senates, 0, n);
		sum--;
	}
	
	//While there are still senates, let 2 from the biggest group or two groups out
	//This will ensure that the last two are from a different group
	while (sum > 0) {
		printf("%c", senates[n - 1].group);
		senates[n - 1].number--;
		sortSenates(senates, 0, n);
		printf("%c ", senates[n - 1].group);
		senates[n - 1].number--;
		sortSenates(senates, 0, n);
		
		sum -= 2;
	}
	
	printf("\n");
	
	free(senates);
	return 0;
}

//Quick sort the groups of senates according to the number of members present in them
void sortSenates(struct Senate *senates, int low, int high) {
	if (high < 2) return;
	
	int i, j, p;
	struct Senate temp;
	
	p = senates[high / 2].number;
	i = low;
	j = high;
	
	while (j > i) {
		while (senates[i].number < p) i++;
		while (senates[j].number > p) j--;
		
		temp.number = senates[i].number;
		temp.group = senates[i].group;
		
		senates[i].number = senates[j].number;
		senates[i].group = senates[j].group;
		
		senates[j].number = temp.number;
		senates[j].group = temp.group;
		
		i++;
		j--;
	}
	
	sortSenates(senates, 0, low);
	sortSenates(senates, low + 1, high - i);
}
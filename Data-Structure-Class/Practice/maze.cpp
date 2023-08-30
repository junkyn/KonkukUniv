#include <iostream>
#include <cstdio>

using namespace std;

class stack {
public:
	int Stack[100];
	int SP;

	int init() {
		SP = 0;
		return 0;
	}
	int isEmpty() {
		return SP == 0;
	}
	int Push(int x) {
		Stack[SP++] = x; return 0;
	}
	int Pop() {
		return Stack[--SP];
	}



};

class queue {
public:
	int N;
	int* Queue;
	int Head, Tail;

	void init() {
		Head = Tail = 0;
	}
	int isEmpty() {
		return Head == Tail;
	}
	int insert(int x) {
		Queue[Head] = x; Head = (Head + 1) % N; return 0;
	}
	int Pop() {
		int RetVal;
		RetVal = Queue[Tail]; Tail = (Tail + 1) % N;
		return RetVal;
	}
	void createQueue(int n) {
		N = n;
		Queue = new int[N];
		init();
	}
};
char Map[8][10] = { '0','1','1','0','0','0','0','1','1','1'
					,'0','1','1','0','1','1','0','1','1','1'
					,'0','0','0','0','0','0','0','1','1','1'
					,'1','1','0','1','1','1','1','1','1','1'
					,'1','1','0','1','1','1','1','1','1','1'
					,'1','0','0','1','1','1','1','0','0','0'
					,'1','0','1','1','1','1','1','0','1','0'
					,'1','0','0','0','0','0','0','0','1','0' };
char MapQ[8][10] = { '0','1','1','0','0','0','0','1','1','1'
					,'0','1','1','0','1','1','0','1','1','1'
					,'0','0','0','0','0','0','0','1','1','1'
					,'1','1','0','1','1','1','1','1','1','1'
					,'1','1','0','1','1','1','1','1','1','1'
					,'1','0','0','1','1','1','1','0','0','0'
					,'1','0','1','1','1','1','1','0','1','0'
					,'1','0','0','0','0','0','0','0','1','0' };

int i, j;

stack S; queue Q;
bool checkRange(int i, int j) {
	if (i < 0 || j < 0 || i>9 || j>9) {
		return false;
	}
	return true;
}

int Find() {
	int ip, jp;
	i = j = 0; Map[i][j] = '*';
	while (true) {
		if (i == 7 && j == 9)return 1;
		ip = -100; jp = -100;
		if (checkRange(i-1,j)&&Map[i - 1][j] == '0') {
			ip = i - 1; jp = j;
		} // * 8, out range 처리
		else if (checkRange(i - 1, j + 1) && Map[i - 1][j + 1] == '0') {
			ip = i - 1, jp= j + 1;
		}
		else if (checkRange(i, j + 1) && Map[i][j + 1] == '0') {
			ip = i; jp = j + 1;
		}
		else if (checkRange(i + 1, j + 1) && Map[i + 1][j + 1] == '0') {
			ip = i + 1; jp = j + 1;
		}
		else if (checkRange(i + 1, j) && Map[i + 1][j] == '0') {
			ip = i + 1; jp = j;
		}
		else if (checkRange(i + 1, j - 1) && Map[i + 1][j - 1] == '0') {
			ip = i + 1; jp = j - 1;
		}
		else if (checkRange(i, j - 1) && Map[i][j - 1] == '0') {
			ip = i; jp = j - 1;
		}
		else if (checkRange(i - 1, j - 1) && Map[i-1][j - 1] == '0') {
			ip = i - 1; jp = j - 1;
		}
		if (ip != -100) { // 전진
			S.Push(i); S.Push(j); i = ip; j = jp; Map[i][j] = '*';
		}
		else { // 후진
			if (S.isEmpty())return-1;
			Map[i][j] = 'X';j= S.Pop(); i = S.Pop();
		}
	}
}

int FindQ() {
	Q.createQueue(100);

	i = 0; j = 0; int ip, jp;
	while (true) {
		if (MapQ[i][j] == '*') {
			if (Q.isEmpty())break;
			else
				i = Q.Pop(); j = Q.Pop();
		}// 포기
		MapQ[i][j] = '*';
		if (MapQ[i - 1][j] == '0') {
			Q.insert(i - 1); Q.insert(j);
		} // * 8, out range 처리
		if (checkRange(i - 1, j + 1) && MapQ[i - 1][j + 1] == '0') {
			Q.insert(i - 1); Q.insert(j + 1);
		}
		if (checkRange(i, j + 1) && MapQ[i][j + 1] == '0') {
			Q.insert(i); Q.insert(j + 1);
		}
		if (checkRange(i + 1, j + 1) && MapQ[i + 1][j + 1] == '0') {
			Q.insert(i + 1); Q.insert(j + 1);
		}
		if (checkRange(i + 1, j) && MapQ[i + 1][j] == '0') {
			Q.insert(i + 1); Q.insert(j);
		}
		if (checkRange(i + 1, j - 1) && MapQ[i + 1][j - 1] == '0') {
			Q.insert(i + 1); Q.insert(j - 1);
		}
		if (checkRange(i, j - 1) && MapQ[i][j - 1] == '0') {
			Q.insert(i); Q.insert(j - 1);
		}
		if (checkRange(i - 1, j - 1) && MapQ[i-1][j - 1] == '0') {
			Q.insert(i - 1); Q.insert(j - 1);
		}
		if (Q.isEmpty())return -1;
		i = Q.Pop(); j = Q.Pop();
	}
}


int main() {
	Find();
	printf("Stack 활용 미로 찾기\n");
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 10; j++) {
			printf("%c ", Map[i][j]);
		}
		printf("\n");
	}

	FindQ();
	printf("\nQueue 활용 미로 찾기\n");
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 10; j++) {
			printf("%c ", MapQ[i][j]);
		}
		printf("\n");
	}
}
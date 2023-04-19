#include <iostream>
#include <cstdio>
//O(n+m) , Use Stack,linkedlist DFS
using namespace std;

class Node {
public:
	int a;
	Node* n;
};
class List {// Linked List
public:
	void Insert(int x);
	int NextForward();
	Node* head;
	Node* LastForward;
};
void List::Insert(int x) { // 간단하게 노드를 앞에 계속 추가
	Node* c;
	c = new Node;
	c->a = x;
	c->n = head; // 노드의 n이 헤드를 가리키게
	head = c; //다시 헤드는 c노드
}
int List::NextForward() {
	if (head == NULL) 
		return -1;
	if (LastForward == NULL) // 처음이네? 
		LastForward = head;  // 헤드주기
	else
		LastForward = LastForward->n; // 처음이 아니야? 그럼 다음거 
	if (LastForward == NULL) // 다음거가 없어?
		return -1; // 끝
	else
		return LastForward->a; // 있으면 그 데이터 반환
}
int n, m;
List MAP[1000];
int Visited[1000];
int Stack[1000];
int LastStart;
int SP;

void Push(int x) {
	Stack[SP++] = x;
}
int Pop() {
	return Stack[--SP];
}
int isEmpty() {
	return (SP == 0);
}
void SetLink(int x, int y) { // 연결~
	MAP[x].Insert(y); // 벡터 x에 y넣음
	return;
}


int isMarked(int x) {
	return Visited[x];
}
void Mark(int x) {
	Visited[x] = 1;
}
int NextStart() {
	LastStart++;
	while (LastStart <= n) {
		if (Visited[LastStart] == 0) // 1부터 올라가서 방문안한 노드 탐색
			return LastStart;
		else
			LastStart++;
	}
	return -1;
}
int main() {

	int i, x, y, cur, s;
	scanf("%d %d", &n, &m);
	for (i = 0; i < m; i++) {
		scanf("%d %d", &x, &y);
		SetLink(x, y);
		SetLink(y, x);
	}

	while ((cur = NextStart()) != -1) { // 방문하지 않은 노드 탐색
		printf("%d", cur); // 방문안한 노드가 현재노드니까 출력
		Mark(cur); // 방문했다고 마크
		while (1) {
			if ((s = MAP[cur].NextForward()) != -1) { // 노드 기준으로 연결된 노드 탐색
				if (isMarked(s) == 0) { // 그 연결된 노드를 방문했는가?
					printf(" %d", s); Mark(s); // 방문안했으면 출력, 방문했다고 마크
					Push(cur); // 스택에 넣기
					cur = s; // 방문한 노드를 다시 현재노드로
				}
				else {

				}
			}
			else { // 연결된 노드가 없다
				if (isEmpty() == 1) // 스택도 비었나?
					break; // 비었으면 break;
				else
					cur = Pop(); // 스택에서 팝 (이전 노드로 이동)
			}
		}
		printf("\n");
	}

	return 0;
}

/*
10 7
1 3
3 9
6 2
5 10
7 3
4 9
8 10


20 20
13 17
1 5
1 13
13 1
5 6
6 5
7 7
19 20
18 19
19 4
15 16
16 2
4 8
8 10
9 15
19 11
7 17
8 19
19 20
1 19


*/
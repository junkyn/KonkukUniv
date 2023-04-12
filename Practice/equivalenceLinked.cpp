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
void List::Insert(int x) { // �����ϰ� ��带 �տ� ��� �߰�
	Node* c;
	c = new Node;
	c->a = x;
	c->n = head; // ����� n�� ��带 ����Ű��
	head = c; //�ٽ� ���� c���
}
int List::NextForward() {
	if (head == NULL) 
		return -1;
	if (LastForward == NULL) // ó���̳�? 
		LastForward = head;  // ����ֱ�
	else
		LastForward = LastForward->n; // ó���� �ƴϾ�? �׷� ������ 
	if (LastForward == NULL) // �����Ű� ����?
		return -1; // ��
	else
		return LastForward->a; // ������ �� ������ ��ȯ
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
void SetLink(int x, int y) { // ����~
	MAP[x].Insert(y); // ���� x�� y����
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
		if (Visited[LastStart] == 0) // 1���� �ö󰡼� �湮���� ��� Ž��
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

	while ((cur = NextStart()) != -1) { // �湮���� ���� ��� Ž��
		printf("%d", cur); // �湮���� ��尡 ������ϱ� ���
		Mark(cur); // �湮�ߴٰ� ��ũ
		while (1) {
			if ((s = MAP[cur].NextForward()) != -1) { // ��� �������� ����� ��� Ž��
				if (isMarked(s) == 0) { // �� ����� ��带 �湮�ߴ°�?
					printf(" %d", s); Mark(s); // �湮�������� ���, �湮�ߴٰ� ��ũ
					Push(cur); // ���ÿ� �ֱ�
					cur = s; // �湮�� ��带 �ٽ� �������
				}
				else {

				}
			}
			else { // ����� ��尡 ����
				if (isEmpty() == 1) // ���õ� �����?
					break; // ������� break;
				else
					cur = Pop(); // ���ÿ��� �� (���� ���� �̵�)
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
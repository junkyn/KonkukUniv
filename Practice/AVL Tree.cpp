#include <iostream>
#include <cstdio>
using namespace std;

class Node {
public:
	int data;
	int LLen, RLen;
	Node* l = NULL;
	Node* r = NULL;
	Node(int x) {
		data = x;
	}
};

class AVL {
private:
	Node* root;
public:
	AVL() {
		root = new Node(9999);
		root->LLen = 0; root->RLen = 0;
	}
	Node* Search(int x);
	Node* Search(Node* NN, int x);
	void Insert(int x);
	int Insert(Node* NN, int x, Node** RP);
	void Print() {
		Print(root, 0, 0);
	}
	void Print(Node* N, int d, int LR);
};
Node* AVL::Search(int x) {
	return Search(root, x);
}
Node* AVL::Search(Node* NN, int x) {
	if (NN == NULL)
		return NULL;
	else if (NN->data == x)
		return NN;
	else if (NN->data > x)
		Search(NN->l, x);
	else
		Search(NN->r, x);
}

void AVL::Insert(int x) {
	int Len;
	Len = Insert(root->l, x, &(root->l));

	root->LLen = Len + 1;
}
int AVL::Insert(Node* N, int x, Node** RP) {
	Node* A, * B, * C, * T1, * T2;
	Node* NN;
	int Len;
	if (N == NULL) {     
		// 현재 노드가 NULL인 경우, 데이터 x를 가진 새로운 노드를 생성하고
		// 왼쪽과 오른쪽 포인터를 NULL로 설정합니다.
		NN = new Node(x);
		NN->LLen = 0; NN->RLen = 0;
		NN->l = NN->r = NULL;
		*RP = NN;
		return 0;
	}
	else {
		if (N->data < x) {
			// 현재 노드의 데이터가 x보다 작은 경우, x를 오른쪽 서브트리에 재귀적으로 삽입합니다.
			Len = Insert(N->r, x, &(N->r));
			N->RLen = Len + 1;
		}
		else if (N->data > x) {
			// 현재 노드의 데이터가 x보다 큰 경우, x를 왼쪽 서브트리에 재귀적으로 삽입합니다.
			Len = Insert(N->l, x, &(N->l));
			N->LLen = Len + 1;
		}
	}
	if (N->LLen > N->RLen + 1 || N->RLen > N->LLen + 1) {
		// 현재 노드의 균형 인수가 1보다 크거나 -1보다 작으면, 트리가 균형이 깨진 것입니다.
		// 회전 작업을 수행하여 트리를 균형 상태로 재조정합니다.

		if(N->data > x && N->l->data > x){ // LL 경우
			 // 현재 노드를 기준으로 왼쪽 회전(LL)을 수행합니다.
			A = N; B = N->l;
			T1 = B->r; B->r = A; A->l = T1;
			if (A->l == NULL) A->LLen = 0;
			else A->LLen = max((A->l)->LLen, (A->l)->RLen)+1;
			B->RLen = max((B->r)->LLen, (B->r)->RLen)+1;
			B->LLen = max((B->l)->LLen, (B->l)->RLen) + 1;
			*RP = B;
			return max(B->LLen, B->RLen);
		}
		else if (N->data > x && (N->l)->data < x) { // LR 경우
			// 현재 노드를 기준으로 이중 회전(LR)을 수행합니다.
			A = N; B = A->l; C = B->r;
			T1 = C->l; T2 = C->r; C->l = B; C->r = A;
			B->r = T1; A->l = T2;
			if (B->r == NULL) B->RLen = 0;
			else B->RLen = max((B->r)->LLen, (B->r)->RLen) + 1;
			if (A->l == NULL) A->LLen = 0;
			else A->LLen = max((A->l)->LLen, (A->l)->RLen) + 1;
			C->LLen = max((C->l)->LLen,(C->l)->RLen) + 1;
			C->RLen = max((C->r)->LLen, (C->r)->RLen) + 1;
			*RP = C;
			return max(C->LLen, C->RLen);
		}
		else if (N->data < x && (N->r)->data < x) { // RR 경우
			// 현재 노드를 기준으로 오른쪽 회전(RR)을 수행합니다.
			A = N; B = N->r;
			T1 = B->l; B->l = A; A->r = T1;
			if (A->r == NULL) A->RLen = 0;
			else A->RLen = max((A->r)->LLen, (A->r)->RLen) + 1;
			B->RLen = max((B->r)->LLen, (B->r)->RLen) + 1;
			B->LLen = max((B->l)->LLen, (B->l)->RLen) + 1;
			*RP = B;
			return max(B->LLen, B->RLen);
		}
		else if (N->data < x && (N->r)->data > x) { // RL 경우
			// 현재 노드를 기준으로 이중 회전(RL)을 수행합니다.
			A = N; B = A->r; C = B->l;
			T1 = C->l; T2 = C->r; C->l = A; C->r = B;
			A->r = T1; B->l = T2;
			if (A->r == NULL) A->RLen = 0;
			else A->RLen = max((A->r)->LLen, (A->r)->RLen) + 1;
			if (B->l == NULL) B->LLen = 0;
			else B->LLen = max((B->l)->LLen, (B->l)->RLen) + 1;
			C->LLen = max((C->l)->LLen, (C->l)->RLen) + 1;
			C->RLen = max((C->r)->LLen, (C->r)->RLen) + 1;
			*RP = C;
			return max(C->LLen, C->RLen);
		}
	}
	else return max(N->LLen, N->RLen); // 균형이 이미 유지되고 있는 경우, 왼쪽과 오른쪽 서브트리 중 더 큰 높이를 반환합니다.
	//return max(N->LLen, N->RLen);
}

void AVL::Print(Node* N, int d, int LR) {
	if (LR == 1)for (int i = 0; i < d; i++) printf("              ");
	printf(" [%2d|%4d|%2d] ", N->LLen,N->data,N->RLen);
	if (N->l == NULL)
		printf("\n");
	else Print(N->l, d + 1, 0);
	if (N->r == NULL);
	else	Print(N->r, d + 1, 1);

}


int main() {
	char c;
	int x;
	AVL t;
	Node* n;
	while (true) {
		t.Print();
		cin >> c;
		if (c == 's') {
			cin >> x;
			if ((n = t.Search(x)) == NULL)
				cout << x << " Not Found!!" << endl;
			else
				cout << x << " Found at address " << n << endl;
		}
		else if (c == 'i') {
			cin >> x;
			if (t.Search(x) == NULL)
				t.Insert(x);
			else
				cout << x << " Already Exist!" << endl;
		}

		else if (c == 'q') {
			break;
		}
		else cout << "???" << endl;
	}
}

#include <iostream>
#include <cstdio>
using namespace std;

class Node {
public:
	int data;
	Node* l = NULL;
	Node *r = NULL;
	Node(int x) {
		data = x;
	}
};

class BST {
private:
	Node* root;
public:
	BST() {
		root = new Node(999);
		
	}
	Node* Search(int x);
	Node* Search(Node* NN, int x);
	Node* Prt(Node* NN, int x);
	void Insert(int x);
	void Insert(Node* NN, Node* V);
	void Delete(int x);
	void Print() {
		Print(root, 0,0);
	}
	void Print(Node* N, int d,int LR);
};
Node* BST::Search(int x) {
	return Search(root, x);
}
Node* BST::Search(Node* NN, int x) {
	if (NN == NULL)
		return NULL;
	else if (NN->data == x)
		return NN;
	else if (NN->data > x)
		Search(NN->l, x);
	else
		Search(NN->r, x);
}
Node* BST::Prt(Node* NN, int x) {
	if (NN->r != NULL && NN->r->data == x)return NN;
	else if (NN->l != NULL && NN->l->data == x)
		return NN;
	else if (NN->data > x)
		Prt(NN->l, x);
	else if (NN->data < x)
		Prt(NN->r, x);

}
void BST::Insert(int x) {
	Node* V;
	V = new Node(x);
	Insert(root, V);
}
void BST::Insert(Node* NN,Node* V) {
	if (NN->data > V->data)
		if (NN->l == NULL) NN->l = V;
		else Insert(NN->l, V);
	else
		if (NN->r == NULL) NN->r = V;
		else Insert(NN -> r, V);
}
void BST::Print(Node* N, int d, int LR) {
	if(LR==1)for (int i = 0; i < d; i++) printf("\t");
	printf("%8d", N->data);
	if (N->l == NULL)
		printf("\n");
	else Print(N->l, d+1, 0);
	if (N->r == NULL);
	else	Print(N->r, d+1,1);

	
	cout << endl;
}
void BST::Delete(int x) {
	Node* PP = Prt(root,x);
	Node* NN = PP->data > x ? PP->l : PP->r;
	Node* SP;
	if (NN == NULL)return;
	if (NN->l == NULL && NN->r == NULL) {
		if (PP->l == NN) PP->l = NULL;
		else PP->r = NULL;
		delete NN;
	}
	else if (NN -> l == NULL && NN->r != NULL) {
		if (PP->l == NN) PP->l = NN->r;
		else PP->r = NN->r;
		delete NN;
	}
	else if (NN->l != NULL && NN->r == NULL) {
		if (PP->l == NN)PP->l = NN->l;
		else PP->r = NN->l;
		delete NN;
	}
	else {
		Node* SP = NN->r;
		while (SP->l != NULL) {
			SP = SP->l;
		}
		if (PP->l == NN)PP->l = SP;
		else PP->r = SP;
		SP->l = NN->l;
		while (SP->r != NULL)
			SP = SP->r;
		SP->r == NN->r;
		delete NN;
	}
}
int main() {
	char c;
	int x;
	BST t;
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
		else if (c == 'd') {
			cin >> x;
			if (t.Search(x) == NULL)
				cout << x << " doesn't exit!" << endl;
			else
				t.Delete(x);
		}
		else if (c == 'q') {
			break;
		}
		else cout << "???" << endl;
	}
}

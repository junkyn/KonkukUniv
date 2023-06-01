#include <iostream>
#include <cstdio>
using namespace std;

//i 10 i 5 i 15 i 3 i 7 i 2 i 4 i 6 i 8 i 13 i 17 i 11 i 14 i 16 i 19

class Node {
public:
	int data;
	Node* l = NULL;
	Node* r = NULL;
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
		Print(root, 0, 0);
	}
	void Print(Node* N, int d, int LR);
	void Preorder() {
		Preorder(root);
	}
	void Preorder(Node* N);
	void Inorder() {
		Inorder(root);
	}
	void Inorder(Node* N);
	
	void Postorder() {
		Postorder(root);
	}
	void Postorder(Node* N);

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
void BST::Insert(Node* NN, Node* V) {
	if (NN->data > V->data)
		if (NN->l == NULL) NN->l = V;
		else Insert(NN->l, V);
	else
		if (NN->r == NULL) NN->r = V;
		else Insert(NN->r, V);
}
void BST::Print(Node* N, int d, int LR) {
	if (LR == 1)for (int i = 0; i < d; i++) printf("\t");
	printf("%8d", N->data);
	if (N->l == NULL)
		printf("\n");
	else Print(N->l, d + 1, 0);
	if (N->r == NULL);
	else	Print(N->r, d + 1, 1);


	cout << endl;
}
void BST::Delete(int x) {
	Node* PP = Prt(root, x);
	Node* NN = PP->data > x ? PP->l : PP->r;
	Node* SP;
	if (NN == NULL)return;
	if (NN->l == NULL && NN->r == NULL) {
		if (PP->l == NN) PP->l = NULL;
		else PP->r = NULL;
		delete NN;
	}
	else if (NN->l == NULL && NN->r != NULL) {
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

void BST::Preorder(Node* N) {
	if (N == NULL) return;
	printf("%d ", N->data);
	Preorder(N->l);
	Preorder(N->r);
}
void BST::Inorder(Node* N) {
	if (N == NULL) return;
	Inorder(N->l);
	printf("%d ", N->data);
	Inorder(N->r);
}
void BST::Postorder(Node* N) {
	if (N == NULL) return;
	Postorder(N->l);
	Postorder(N->r);
	printf("%d ", N->data);
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
		else if (c == 'p') {
			t.Preorder();
			printf("\n");
		}
		else if (c == 'm') {
			t.Inorder();
			printf("\n");
		}
		else if (c == 'b') {
			t.Postorder();
			printf("\n");
		}
		else cout << "???" << endl;
	}
}

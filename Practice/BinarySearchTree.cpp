#include <iostream>
#include <cstdio>
using namespace std;

class Node { // 트리 노드
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
	Node* root; // 뿌리 노드
public:
	BST() {
		root = new Node(999); // 999로 뿌리노드 설정
		
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
Node* BST::Search(int x) { // 껍데기
	return Search(root, x);
}
Node* BST::Search(Node* NN, int x) { // 찐
	if (NN == NULL) // NULL 까지 왔는데 못찾았으면 없는거!
		return NULL;
	else if (NN->data == x) // 검색됐으면 NN 주솟값 리턴
		return NN;
	else if (NN->data > x) // x가 더 작으면 왼쪽 자식 노드가서 다시 검색
		Search(NN->l, x);
	else // 크면~~
		Search(NN->r, x);
}
Node* BST::Prt(Node* NN, int x) { // Delete용 부모노드 찾기
	if (NN->r != NULL && NN->r->data == x)return NN; // 오른쪽 자식 노드 있고 그 데이터 x면 부모리턴
	else if (NN->l != NULL && NN->l->data == x) // 왼쪽자식노드가~~~
		return NN;
	else if (NN->data > x) // 자식중에 x가 없고, x가 더 작으면 왼쪽으로
		Prt(NN->l, x);
	else if (NN->data < x) // 반대로~~
		Prt(NN->r, x);

}
void BST::Insert(int x) { // 껍데기
	Node* V;
	V = new Node(x); // x가진 노드 생성
	Insert(root, V); // 뿌리부터 ㄱ
}
void BST::Insert(Node* NN,Node* V) {
	if (NN->data > V->data) // 작으면 왼쪽 자식으로~
		if (NN->l == NULL) NN->l = V; // 왼쪽 자식없으면 그자리에
		else Insert(NN->l, V); // 있으면 걔로 다시 돌려
	else // 반대도 똑같
		if (NN->r == NULL) NN->r = V;
		else Insert(NN -> r, V);
}
void BST::Print(Node* N, int d, int LR) { 
	if(LR==1)for (int i = 0; i < d; i++) printf("\t"); // LR이 1이라는건 R까지 입력했다는것, d는 깊이, 깊이만큼 탭 입력
	printf("%8d", N->data); // 8칸으로
	if (N->l == NULL) // 왼쪽이 없으면
		printf("\n"); // 그냥 줄바꿔
	else Print(N->l, d+1, 0); // 있으면 깊이 늘려서 다시
	if (N->r == NULL);
	else	Print(N->r, d+1,1);
// 왼쪽 노드 전부 출력후 하나씩 위로 오면서 오른쪽 출력하는 방식
	
	cout << endl;
}
void BST::Delete(int x) {
	Node* PP = Prt(root,x); // x의 부모노드 
	Node* NN = PP->data > x ? PP->l : PP->r; //x의 노드찾음
	Node* SP; // x의 노드가 자식노드를 전부 가질때를 위한 특수 노드
	if (NN == NULL)return; // x가 없으면 x노드가 없다는거지 끝
	if (NN->l == NULL && NN->r == NULL) { // x노드 자식이 없으면
		if (PP->l == NN) PP->l = NULL; // x가 부모의 왼쪽 노드라면 왼쪽 노드 연결끊음
		else PP->r = NULL; // 아니면 오른쪽 노드 연결끊음
		delete NN; // 연결없어진 x노드 삭제
	}
	else if (NN -> l == NULL && NN->r != NULL) { // 오른쪽 자식이 있을 때
		if (PP->l == NN) PP->l = NN->r; // x노드가 부모의 왼쪽일때, 부모의 왼쪽을 x의 오른쪽으로 연결
		else PP->r = NN->r; // 반대면 부모의 오른쪽을 x의 오른쪽으로 연결
		delete NN; // 쩌리가 된 x노드 삭제 
	}
	else if (NN->l != NULL && NN->r == NULL) { // 왼쪽 자식이 있을때, ~~~
		if (PP->l == NN)PP->l = NN->l;
		else PP->r = NN->l;
		delete NN;
	}
	else { // 자식이 2명일때!!
		Node* SP = NN->r; // 스페셜노드를 x노드의 오른쪽 노드로 (x보다 더 큰 곳으로 보내버림)
		while (SP->l != NULL) { // 왼쪽 자식이 없을 때까지!
			SP = SP->l; // 왼쪽으로 쭉 보냄 (결국 x보다 큰 것중에 가장 작은놈이 됨!)
		}
		if (PP->l == NN)PP->l = SP; // x노드가 부모의 왼쪽자식이면? 부모의 왼쪽자식을 SP로 대체
		else PP->r = SP; // 아니면 ~
		SP->l = NN->l; // SP의 왼쪽 노드를 x노드의 왼쪽노드로 대체 (SP는 x보다 크고 왼쪽자식없으니 가능)
		while (SP->r != NULL) // SP 오른쪽자식 없을때까지 
			SP = SP->r;// SP의 오른쪽으로 쭉 보냄 (SP보단 크고 SP부모보단 작은것 중 젤 큰놈)
		SP->r == NN->r; // 그 놈의 오른쪽 자식에 x노드의 오른쪽이었던 걸 연결 (그거보단 크고 젤 작은게 x노드의 오른쪽 자식일테니까 가능)
		delete NN; // 자식 잃은 NN 삭제
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

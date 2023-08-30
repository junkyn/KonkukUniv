#include <iostream>

using namespace std;

class JkchoiStudent {
private:
	string name="";
	float score=0;
public:
	JkchoiStudent() {

	}
	JkchoiStudent(string name, float score) {
		this->name = name;
		this->score = score;
	}
	void View(ostream& os)const {
		os << "-------------------------\n" << "�̸� : " << name << "\n���� : " << score << endl;
	}
	float getScore() {
		return score;
	}
	
};
ostream& operator<<(ostream& os, const JkchoiStudent &stu) {
	stu.View(os);
	return os;
}
ostream& operator<<(ostream& os, const JkchoiStudent* stu) {
	stu->View(os);
	return os;
}
class JkchoiScholarShip {
private:
	string name = "";
	JkchoiStudent* mem=NULL;
	int size = 0;
	int min = 0;
	int idx = 0;
public:
	JkchoiScholarShip() {
	}
	JkchoiScholarShip(string name, int n) {
		this->name = name;
		size = n;
		mem = new JkchoiStudent[size];
	}
	JkchoiScholarShip(JkchoiScholarShip& jk) {
		this->name = jk.name;
		this->size = jk.size;
		this->mem = new JkchoiStudent[this->size];
		this->min = jk.min;
		this->idx = jk.idx;
		for (int i = 0; i < idx; i++) {
			this->mem[i] = jk.mem[i];
		}
	}
	~JkchoiScholarShip() {
		delete[] mem;
	}

	void apply(JkchoiStudent* stu) {
		if (idx < size) {
			mem[idx++] = *stu;
			cout << "��û �Ϸ�" << endl;
		}
		else {
			if (stu->getScore() < mem[min].getScore()) {
				cout << "��û���� ����" << endl;
			}
			else {
				mem[min] = *stu;
				cout << "��û �Ϸ�" << endl;
			}
		}
		JkchoiStudent tmp;
		for (int i = 0; i < idx; i++) {
			for (int j = i + 1; j < idx; j++) {
				if (mem[i].getScore() < mem[j].getScore()) {
					tmp = mem[i];
					mem[i] = mem[j];
					mem[j] = tmp;
				}
			}
		}
		min = idx-1;
	}
	void View(ostream& os)const {
		float sum = 0;
		for (int i = 0; i < idx; i++) {
			sum += mem[i].getScore();
		}

		cout << "���бݸ� : " << name << "\n" <<
			"�����ο� : " << size << "\n" <<
			"������� : " << (float)sum / idx << "\n" <<
			"��û�ڼ� : " << idx << endl;
		for (int i = 0; i < idx; i++) {
			cout << mem[i] << endl;
		}
	}
	void pointerView() {
		cout << &mem << endl;
	}
	
};

ostream& operator<<(ostream& os, const JkchoiScholarShip& sch) {
	sch.View(os);
	return os;
}
ostream& operator<<(ostream& os, const JkchoiScholarShip* sch) {
	sch->View(os);
	return os;
}

int main() {
	cout << "202211389 ���ر�" << endl;
	JkchoiStudent bluejoa;
	JkchoiStudent greenjoa("ȫ�浿", 5.5);
	cout << greenjoa << endl;

	JkchoiScholarShip scholarship;
	JkchoiScholarShip scholarship1("�Ǳ����б�", 3);

	scholarship1.apply(new JkchoiStudent("ȫ�浿", 4.5));
	scholarship1.apply(new JkchoiStudent("��浿", 2.5));
	scholarship1.apply(new JkchoiStudent("��浿", 3.5));
	scholarship1.apply(new JkchoiStudent("�̱浿", 1.5));
	scholarship1.apply(new JkchoiStudent("�ֱ浿", 5.5));

	cout << scholarship1 << endl;

	JkchoiScholarShip scholarship2(scholarship1);
	cout << scholarship2 << endl;
	scholarship1.pointerView();
	scholarship2.pointerView();

}
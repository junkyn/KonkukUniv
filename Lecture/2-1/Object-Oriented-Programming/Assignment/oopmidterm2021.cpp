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
		os << "-------------------------\n" << "이름 : " << name << "\n학점 : " << score << endl;
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
			cout << "신청 완료" << endl;
		}
		else {
			if (stu->getScore() < mem[min].getScore()) {
				cout << "신청하지 못함" << endl;
			}
			else {
				mem[min] = *stu;
				cout << "신청 완료" << endl;
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

		cout << "장학금명 : " << name << "\n" <<
			"가능인원 : " << size << "\n" <<
			"평균학점 : " << (float)sum / idx << "\n" <<
			"신청자수 : " << idx << endl;
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
	cout << "202211389 최준규" << endl;
	JkchoiStudent bluejoa;
	JkchoiStudent greenjoa("홍길동", 5.5);
	cout << greenjoa << endl;

	JkchoiScholarShip scholarship;
	JkchoiScholarShip scholarship1("건국장학금", 3);

	scholarship1.apply(new JkchoiStudent("홍길동", 4.5));
	scholarship1.apply(new JkchoiStudent("김길동", 2.5));
	scholarship1.apply(new JkchoiStudent("고길동", 3.5));
	scholarship1.apply(new JkchoiStudent("이길동", 1.5));
	scholarship1.apply(new JkchoiStudent("최길동", 5.5));

	cout << scholarship1 << endl;

	JkchoiScholarShip scholarship2(scholarship1);
	cout << scholarship2 << endl;
	scholarship1.pointerView();
	scholarship2.pointerView();

}
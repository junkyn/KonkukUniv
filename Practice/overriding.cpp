#include <iostream>
#include <string>

using namespace std;
class Skill {
private:
	string name;
	int mana;
	int damage;
public:
	Skill() {};
	Skill(string name, int mana, int damage) :name(name), mana(mana), damage(damage) {
	}
	friend ostream& operator << (ostream& out, Skill& s) {
		if (s.damage == 0) {
			out << s.name << "�� ����ߴ�.";
		}
		else
			out << s.name << "�� ����� " << s.damage << "�� ���ظ� �־���.";
		return out;
	}
	int getMana() {
		return mana;
	}
};

class Champion {
protected:
	string name;
	Skill Q;
	Skill W;
	Skill E;
	Skill R;
	int HP;
	int MP;
public:
	virtual void doSkill(char c) = 0;
};
class CostChampion : public Champion {
private:
public:
	void doSkill(char c) {
		Skill tmp;
		switch (c) {
		case 'Q': tmp = Q; break;
		case 'W': tmp = W; break;
		case 'E': tmp = E; break;
		case 'R': tmp = R; break;
		}
		if (MP < tmp.getMana()) {
			cout << name << "��(��) " << "������ ������ ��ų�� ������� ���ߴ�." << endl;
			return;
		}
		MP -= tmp.getMana();

		cout << name << "��(��) " << tmp << "���� ���� : " << MP << endl;
	}
};
class NoCostChampion : public Champion {
private:
	int MP = 0;
public:
	NoCostChampion() {};
	void doSkill(char c) {
		Skill tmp;
		switch (c) {
		case 'Q': tmp = Q; break;
		case 'W': tmp = W; break;
		case 'E': tmp = E; break;
		case 'R': tmp = R; break;
		}
		cout << name << "��(��) " << tmp<<endl;
	}
};
class Akali : public NoCostChampion {
public:
	Akali() {
		this->name = "��Į��";
		this->Q = Skill("������ô��", 0, 70);
		this->W = Skill("Ȳȥ�� �帷", 0, 0);
		this->E = Skill("ǥâ�", 0, 30);
		this->R = Skill("����ó��", 0, 80);
	}
};
class Teemo : public CostChampion {
public:
	Teemo() {
		this->name = "Ƽ��";
		this->Q = Skill("�Ǹ� ��Ʈ", 70, 80);
		this->W = Skill("�ż��� �̵�", 40, 0);
		this->E = Skill("�͵� ��Ʈ", 0, 0);
		this->R = Skill("������ ����", 35, 200);
		this->MP = 400;
	}
};
int main() {
	Akali ak;
	Teemo tm;
	ak.doSkill('Q');
	tm.doSkill('R');
	tm.doSkill('R');
	ak.doSkill('W');
	ak.doSkill('E');
	tm.doSkill('Q');
	ak.doSkill('R');
	tm.doSkill('E');
	ak.doSkill('Q');
	tm.doSkill('W');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');
	tm.doSkill('R');

}
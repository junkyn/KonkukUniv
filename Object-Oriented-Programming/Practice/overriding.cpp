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
			out << s.name << "을 사용했다.";
		}
		else
			out << s.name << "을 사용해 " << s.damage << "의 피해를 주었다.";
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
			cout << name << "은(는) " << "마나가 부족해 스킬을 사용하지 못했다." << endl;
			return;
		}
		MP -= tmp.getMana();

		cout << name << "은(는) " << tmp << "현재 마나 : " << MP << endl;
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
		cout << name << "은(는) " << tmp<<endl;
	}
};
class Akali : public NoCostChampion {
public:
	Akali() {
		this->name = "아칼리";
		this->Q = Skill("오연투척검", 0, 70);
		this->W = Skill("황혼의 장막", 0, 0);
		this->E = Skill("표창곡예", 0, 30);
		this->R = Skill("무결처형", 0, 80);
	}
};
class Teemo : public CostChampion {
public:
	Teemo() {
		this->name = "티모";
		this->Q = Skill("실명 다트", 70, 80);
		this->W = Skill("신속한 이동", 40, 0);
		this->E = Skill("맹독 다트", 0, 0);
		this->R = Skill("유독성 함정", 35, 200);
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
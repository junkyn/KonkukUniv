#include <iostream>
#include <string>
using namespace std;

class Buy {
	static int income; // 클래스 변수 수입 선언
private:

public:

	Buy(string s) { // 생성자 
		if (s.compare("사과")==0) { // 매개변수가 사과면
			addincome(500); // 수입에 500 추가
		}
		else if (s.compare("포도")==0) { // 매개변수가 포도면
			addincome(700); // 수입에 700 추가
		}
		else if (s.compare("배")==0) { // 매개변수가 배면
			addincome(600); // 수입에 600 추가
		}
		else if (s.compare("오렌지")==0) { // 매개변수가 오렌지면
			addincome(650); // 수입에 650 추가
		}
		else { // 매개변수가 사과,포도,배,오렌지가 아니라면
			cout << "그런 상품은 없어요!" << endl; // 없다고 출력
		}
	}

	static void addincome(int price) { // 수입에 매개변수만큼 더해줌
		income += price;
	}
	static void showIncome() {
		cout <<"총 수입 : " << income << "원" << endl;
	}
};
int Buy::income = 0;
int main() {
	string input;
	while (1) {
		cout << "과일가게에용 사과(500원), 포도(700원), 배(600원), 오렌지(650원) 있어용" << endl;
		cout << "사고 싶은 과일을 입력해주세요(싫으면 q를 입력해줘요) : ";
		cin >> input;
		if (input.compare("q")==0)
			return 0;
		else {
			Buy b(input);
		}
		Buy::showIncome();
	}
}
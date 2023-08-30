#define _CRT_SECURE_NO_WARNINGS // 컴파일러에서 발생하는 경고를 무시하는 매크로

#include <iostream> // 입출력을 위한 헤더 파일
#include <cstdio> // 입출력을 위한 헤더 파일
using namespace std; // 표준 라이브러리의 요소들을 사용하기 위한 namespace 선언

class PQMin { // PQMin 클래스 선언
public:
	PQMin(); // 생성자 선언

	int Size(); // 크기 반환 메서드 선언
	int Top(); // 가장 작은 원소 반환 메서드 선언

	void insert(int x); // 원소 삽입 메서드 선언
	void Delete(); // 원소 삭제 메서드 선언
	int a[1000]; // 원소를 저장하는 배열
	int n; // 원소 개수를 저장하는 변수
};

PQMin::PQMin() { // PQMin 클래스 생성자 정의
	n = 0; // 원소 개수를 0으로 초기화
}

int PQMin::Size() { // 크기 반환 메서드 정의
	return n; // 원소 개수 반환
}

int PQMin::Top() { // 가장 작은 원소 반환 메서드 정의
	return a[1]; // 배열의 첫 번째 원소 반환
}

void PQMin::insert(int x) { // 원소 삽입 메서드 정의
	int i; // 반복문을 위한 변수
	n++; // 원소 개수 증가
	a[n] = x; // 배열의 마지막에 원소 삽입
	i = n; // i를 n으로 초기화
	while (i != 1) { // 루트 노드가 아닐 때까지 반복
		if (a[i / 2] <= a[i]) // 부모 노드와 비교하여 정렬 순서를 만족할 때
			break; // 반복 종료
		swap(a[i / 2], a[i]); // 부모 노드와 자식 노드의 위치 교환
		i = i / 2; // 부모 노드로 이동
	}
}

void PQMin::Delete() { // 원소 삭제 메서드 정의
	int i, j; // 반복문을 위한 변수
	a[1] = a[n]; // 마지막 원소를 루트 노드로 이동
	n--; // 원소 개수 감소
	i = 1; // i를 1로 초기화
	while (true) { // 무한 루프
		if (i * 2 > n) break; // 자식 노드가 없을 때 반복 종료
		if (i * 2 == n) { // 왼쪽 자식 노드만 있는 경우
			if (a[i] <= a[i * 2]) // 자식 노드와 비교하여 정렬 순서를 만족할 때
				break; // 반복 종료
			else { // 정렬 순서를 만족하지 않을 때
				swap(a[i], a[i * 2]); // 자식 노드와 위치 교환
				i = i * 2; // 자식 노드로 이동
			}
		}
		else { // 양쪽 자식 노드가 있는 경우
			if (a[i * 2] < a[i * 2 + 1]) j = i * 2; // 왼쪽 자식 노드가 오른쪽 자식 노드보다 작을 때
			else j = i * 2 + 1; // 오른쪽 자식 노드가 왼쪽 자식 노드보다 작을 때
			if (a[i] <= a[j]) // 자식 노드와 비교하여 정렬 순서를 만족할 때
				break; // 반복 종료
			else { // 정렬 순서를 만족하지 않을 때
				swap(a[i], a[j]); // 자식 노드와 위치 교환
				i = j; // 자식 노드로 이동
			}
		}
	}
}

class PQMax { // PQMax 클래스 선언
public:
	PQMax(); // 생성자 선언

	int Size(); // 크기 반환 메서드 선언
	int Top(); // 가장 큰 원소 반환 메서드 선언

	void insert(int x); // 원소 삽입 메서드 선언
	void Delete(); // 원소 삭제 메서드 선언
	int a[1000]; // 원소를 저장하는 배열
	int n; // 원소 개수를 저장하는 변수
};

PQMax::PQMax() { // PQMax 클래스 생성자 정의
	n = 0; // 원소 개수를 0으로 초기화
}

int PQMax::Size() { // 크기 반환 메서드 정의
	return n; // 원소 개수 반환
}

int PQMax::Top() { // 가장 큰 원소 반환 메서드 정의
	return a[1]; // 배열의 첫 번째 원소 반환
}

void PQMax::insert(int x) { // 원소 삽입 메서드 정의
	int i; // 반복문을 위한 변수
	n++; // 원소 개수 증가
	a[n] = x; // 배열의 마지막에 원소 삽입
	i = n; // i를 n으로 초기화
	while (i != 1) { // 루트 노드가 아닐 때까지 반복
		if (a[i / 2] >= a[i]) // 부모 노드와 비교하여 정렬 순서를 만족할 때
			break; // 반복 종료
		swap(a[i / 2], a[i]); // 부모 노드와 자식 노드의 위치 교환
		i = i / 2; // 부모 노드로 이동
	}
}

void PQMax::Delete() { // 원소 삭제 메서드 정의
	int i, j; // 반복문을 위한 변수
	a[1] = a[n]; // 마지막 원소를 루트 노드로 이동
	n--; // 원소 개수 감소
	i = 1; // i를 1로 초기화
	while (true) { // 무한 루프
		if (i * 2 > n) break; // 자식 노드가 없을 때 반복 종료
		if (i * 2 == n) { // 왼쪽 자식 노드만 있는 경우
			if (a[i] >= a[i * 2]) // 자식 노드와 비교하여 정렬 순서를 만족할 때
				break; // 반복 종료
			else { // 정렬 순서를 만족하지 않을 때
				swap(a[i], a[i * 2]); // 자식 노드와 위치 교환
				i = i * 2; // 자식 노드로 이동
			}
		}
		else { // 양쪽 자식 노드가 있는 경우
			if (a[i * 2] > a[i * 2 + 1]) j = i * 2; // 왼쪽 자식 노드가 오른쪽 자식 노드보다 큰 때
			else j = i * 2 + 1; // 오른쪽 자식 노드가 왼쪽 자식 노드보다 큰 때
			if (a[i] >= a[j]) // 자식 노드와 비교하여 정렬 순서를 만족할 때
				break; // 반복 종료
			else { // 정렬 순서를 만족하지 않을 때
				swap(a[i], a[j]); // 자식 노드와 위치 교환
				i = j; // 자식 노드로 이동
			}
		}
	}
}

int main() { // 메인 함수 시작
	char c; // 입력을 받기 위한 문자 변수
	int x, y, i, lcnt, rcnt; // 정수 변수
	PQMax Left; // PQMax 클래스의 객체 Left 생성
	PQMin Right; // PQMin 클래스의 객체 Right 생성
	lcnt = rcnt = 0; // 왼쪽 원소 개수와 오른쪽 원소 개수를 0으로 초기화
	while (true) { // 무한 루프
		scanf(" %c", &c); // 문자 입력 받기
		if (c == 'p') { // 입력이 'p'인 경우
			if (Left.Size() == 0) // 왼쪽 원소가 없는 경우
				printf("No entry.\n"); // "No entry." 출력
			else // 왼쪽 원소가 있는 경우
				printf("Middle member = %d\n", Left.Top()); // "Middle member = x" 형식으로 중간 원소 출력
		}
		else if (c == 'i') { // 입력이 'i'인 경우
			scanf("%d", &x); // 정수 입력 받기
			if (lcnt == 0) { // 왼쪽 원소가 없는 경우
				Left.insert(x); // 왼쪽에 원소 삽입
				lcnt++; // 왼쪽 원소 개수 증가
			}
			else if (rcnt == 0) { // 오른쪽 원소가 없는 경우
				if (x <= Left.Top()) { // 삽입할 원소가 왼쪽의 최대 원소보다 작거나 같을 때
					Left.insert(x); // 왼쪽에 원소 삽입
					rcnt++; // 오른쪽 원소 개수 증가
				}
				else { // 삽입할 원소가 왼쪽의 최대 원소보다 큰 경우
					Right.insert(x); // 오른쪽에 원소 삽입
					rcnt++; // 오른쪽 원소 개수 증가
				}
			}
			else if (lcnt > rcnt) { // 왼쪽 원소 개수가 오른쪽 원소 개수보다 많은 경우
				if (x <= Left.Top()) { // 삽입할 원소가 왼쪽의 최대 원소보다 작거나 같을 때
					Right.insert(Left.Top()); // 왼쪽의 최대 원소를 오른쪽에 삽입
					Left.Delete(); // 왼쪽의 최대 원소 삭제
					Left.insert(x); // 왼쪽에 원소 삽입
					rcnt++; // 오른쪽 원소 개수 증가
				}
				else { // 삽입할 원소가 왼쪽의 최대 원소보다 큰 경우
					Right.insert(x); // 오른쪽에 원소 삽입
					rcnt++; // 오른쪽 원소 개수 증가
				}
			}
			else { // 왼쪽 원소 개수와 오른쪽 원소 개수가 같은 경우
				if (x >= Right.Top()) { // 삽입할 원소가 오른쪽의 최소 원소보다 크거나 같을 때
					Left.insert(Right.Top()); // 오른쪽의 최소 원소를 왼쪽에 삽입
					Right.Delete(); // 오른쪽의 최소 원소 삭제
					Right.insert(x); // 오른쪽에 원소 삽입
					lcnt++; // 왼쪽 원소 개수 증가
				}
				else { // 삽입할 원소가 오른쪽의 최소 원소보다 작은 경우
					Left.insert(x); // 왼쪽에 원소 삽입
					lcnt++; // 왼쪽 원소 개수 증가
				}
			}
		}
		else if (c == 'q') { // 입력이 'q'인 경우
			if (Left.Size() == 0 && Right.Size() == 0) // 왼쪽과 오른쪽에 원소가 없는 경우
				break; // 반복 종료
			if (lcnt > rcnt) { // 왼쪽 원소 개수가 오른쪽 원소 개수보다 많은 경우
				Right.insert(Left.Top()); // 왼쪽의 최대 원소를 오른쪽에 삽입
				Left.Delete(); // 왼쪽의 최대 원소 삭제
				lcnt--; // 왼쪽 원소 개수 감소
			}
			else if (lcnt < rcnt) { // 왼쪽 원소 개수가 오른쪽 원소 개수보다 적은 경우
				Left.insert(Right.Top()); // 오른쪽의 최소 원소를 왼쪽에 삽입
				Right.Delete(); // 오른쪽의 최소 원소 삭제
				rcnt--; // 오른쪽 원소 개수 감소
			}
			else { // 왼쪽 원소 개수와 오른쪽 원소 개수가 같은 경우
				if (lcnt > 0) { // 왼쪽에 원소가 있는 경우
					Left.Delete(); // 왼쪽의 최대 원소 삭제
					lcnt--; // 왼쪽 원소 개수 감소
				}
				else { // 오른쪽에 원소가 있는 경우
					Right.Delete(); // 오른쪽의 최소 원소 삭제
					rcnt--; // 오른쪽 원소 개수 감소
				}
			}
		}
	}
	return 0; // 프로그램 종료
}

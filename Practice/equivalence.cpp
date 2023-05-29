#include <iostream>
#include <cstdio>
#include <time.h>

//O(n^2) , Use Stack, DFS
using namespace std;

int n,m; 
int MAP[1000][1000]; // ±æÀÖÀ¸¸é 1 ¾øÀ¸¸é 0
int Visited[1000]; // ¹æ¹®ÇßÀ¸¸é 1 ¾ÈÇŞÀ¸¸é 0
int Stack[1000]; // ¹æ¹®ÇÑ°Å ¿©±â´Ù ³ÖÀ½
int LastStart;
int SP;

void Push(int x) {
	Stack[SP++] = x;
}
int Pop() {
	return Stack[--SP];
}  
int isEmpty() {
	return (SP == 0);
}
void SetLink(int x, int y) { // ì—°ê²°~
	MAP[x][y] = 1;
	return;
}
int LastForward[1000];
<<<<<<< Updated upstream
int NextForward(int x) { // x³ëµå¿Í ¿¬°áµÈ ³ëµå Ã£´Âµ¥ 1ºÎÅÍ ÂùÂùÈ÷ Ã£À½
	LastForward[x]++; // ÃÊ±â°ª 0ÀÎµ¥ 1ºÎÅÍ´Ï±î ÇÏ³ª Áõ°¡ÇÏ¸é¼­ ½ÃÀÛ
	while (LastForward[x] <= n) { // ÃÖ´ñ°ªº¸´Ù ÀÛÀ¸¸é~
		if (MAP[x][LastForward[x]] == 1) // x°¡ ÀÌ°É °¥¼öÀÖ³ª¿ä?
			return LastForward[x]; //°¥ ¼ö ÀÖ³×
=======
int NextForward(int x) { // xë…¸ë“œì™€ ì—°ê²°ëœ ë…¸ë“œ ì°¾ëŠ”ë° 1ë¶€í„° ì°¬ì°¬íˆ ì°¾ìŒ
	LastForward[x]++;
	while (LastForward[x] <= n) {
		if (MAP[x][LastForward[x]] == 1) 
			return LastForward[x];
>>>>>>> Stashed changes
		else
			LastForward[x]++; // ¸ø°¡³× ´ÙÀ½°Å °Ë»ö -> °á±¹ 1ºÎÅÍ n±îÁö º»´Ù´Â°Å
		
	}
	return -1; // ±æÀÌ ¾ø¾î¿ä


}
int isMarked(int x) {
	return Visited[x]; 
}
void Mark(int x) {
	Visited[x] = 1;
}
int NextStart() {
	LastStart++;
<<<<<<< Updated upstream
	while (LastStart <= n) { 
		if (Visited[LastStart] == 0) // 1ºÎÅÍ ¿Ã¶ó°¡¼­ ¹æ¹®¾ÈÇÑ ³ëµå Å½»ö
=======
	while (LastStart <= n) {
		if (Visited[LastStart] == 0) // 1ë¶€í„° ì˜¬ë¼ê°€ì„œ ë°©ë¬¸ì•ˆí•œ ë…¸ë“œ íƒìƒ‰
>>>>>>> Stashed changes
			return LastStart;
		else
			LastStart++;
	}
	return -1;
}
int main() {

	int i,x,y,cur,s;
	scanf("%d %d", &n, &m);
	for (i = 0; i < m; i++) {
		scanf("%d %d" ,&x,&y);
		SetLink(x, y);
		SetLink(y, x);
	}
<<<<<<< Updated upstream
	while ((cur = NextStart()) != -1) { // ¹æ¹®ÇÏÁö ¾ÊÀº ³ëµå Å½»ö, Ã³À½ laststart´Â 0
		printf("%d", cur); // ¹æ¹®¾ÈÇÑ ³ëµå°¡ ÇöÀç³ëµå´Ï±î Ãâ·Â
		Mark(cur); // ¹æ¹®Çß´Ù°í ¸¶Å©
=======

	while ((cur = NextStart()) != -1) { // ë°©ë¬¸í•˜ì§€ ì•Šì€ ë…¸ë“œ íƒìƒ‰
		printf("%d", cur); // ë°©ë¬¸ì•ˆí•œ ë…¸ë“œê°€ í˜„ì¬ë…¸ë“œë‹ˆê¹Œ ì¶œë ¥
		Mark(cur); // ë°©ë¬¸í–ˆë‹¤ê³  ë§ˆí¬
>>>>>>> Stashed changes
		while (1) {
			if ((s = NextForward(cur)) != -1) { // ë…¸ë“œ ê¸°ì¤€ìœ¼ë¡œ ì—°ê²°ëœ ë…¸ë“œ íƒìƒ‰
				if (isMarked(s) == 0) { // ê·¸ ì—°ê²°ëœ ë…¸ë“œë¥¼ ë°©ë¬¸í–ˆëŠ”ê°€?
					printf(" %d", s); Mark(s); // ë°©ë¬¸ì•ˆí–ˆìœ¼ë©´ ì¶œë ¥, ë°©ë¬¸í–ˆë‹¤ê³  ë§ˆí¬
					Push(cur); // ìŠ¤íƒì— ë„£ê¸°
					cur = s; // ë°©ë¬¸í•œ ë…¸ë“œë¥¼ ë‹¤ì‹œ í˜„ì¬ë…¸ë“œë¡œ
				}
				else {

				}
			}
			else { // ì—°ê²°ëœ ë…¸ë“œê°€ ì—†ë‹¤
				if (isEmpty() == 1) // ìŠ¤íƒë„ ë¹„ì—ˆë‚˜?
					break; // ë¹„ì—ˆìœ¼ë©´ break;
				else
					cur = Pop(); // ìŠ¤íƒì—ì„œ íŒ (ì´ì „ ë…¸ë“œë¡œ ì´ë™)
			}
		}


		printf("\n");
	}

	return 0;
}

/*
10 7
1 3
3 9
6 2
5 10
7 3
4 9
8 10





*/
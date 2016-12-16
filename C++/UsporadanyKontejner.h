#ifndef USPORADANY_KONTEJNER_H
#define USPORADANY_KONTEJNER_H

#include <deque>
#include <stdexcept>

template<typename T, typename Comparer = std::less<T>>
class UsporadanyKontejner{
	std::deque<T> pole;
public:
	class iterator{
		size_t indexIteratoru;
		std::deque<T> poleHodnot;
	public:
		iterator(const std::deque<T>& _poleHodnot, const size_t& _indexIteratoru) : poleHodnot(_poleHodnot), indexIteratoru(_indexIteratoru){}

		bool operator==(const iterator& iterator)const{
			return indexIteratoru == iterator.indexIteratoru;
		}
		bool operator!=(const iterator& iterator)const{
			return !(operator==(iterator));
		}
		iterator& operator[](size_t _indexIteratoru){
			indexIteratoru = _indexIteratoru;
			return *this;
		}

		iterator& operator++()const{
			indexIteratoru++;
			return *this;
		}
		T& operator*()const{
			return poleHodnot[indexIteratoru];
		}
		size_t operator&()const{
			return indexIteratoru;
		}
	};

	class reverse_iterator
	{
		size_t indexIteratoru;
		std::deque<T> poleHodnot;
	public:
		reverse_iterator(const std::deque<T>& _poleHodnot, const size_t& _indexIteratoru) : poleHodnot(_poleHodnot), indexIteratoru(_indexIteratoru){}

		bool operator==(const iterator& iterator)const{
			return indexIteratoru == iterator.indexIteratoru;
		}
		bool operator!=(const iterator& iterator)const{
			return !(operator==(iterator));
		}
		reverse_iterator& operator++()const{
			indexIteratoru--;
			return *this;
		}
		T& operator*()const{
			return poleHodnot[indexIteratoru];
		}
		size_t operator&()const{
			return indexIteratoru;
		}
	};

	void Vloz(T prvek){
		if (pole.empty()) {
			pole.push_front(prvek);
			return;
		}

		Comparer compare;

		for (size_t i = 0; i < pole.size(); i++) {
			if (!(compare(pole.at(i), prvek))) {
				pole.insert(pole.begin() + i, prvek);
				return;
			}
		}
		pole.push_back(prvek);
	}
	void Odeber(iterator it){
		pole.erase(pole.begin() + &it);
	}

	T& operator[](size_t index){
		return pole[index];
	}
	const T& operator[](size_t index) const{
		return pole[index];
	}

	template<typename K>
	void Vykonej(void(*ukazatel)(K&)){
		for (iterator it = this->begin(); it != this->end(); ++it){
			if (dynamic_cast<K>(*it) != NULL) {
				ukazatel(dynamic_cast<K>(*it));
			}
		}
	}

	template<typename K>
	K& DejPrvni(size_t& index){
		for (UsporadanyKontejner::iterator it = this->begin(); it != this->end(); ++it)
		{
			if (dynamic_cast<K>(*it) != NULL)
			{
				index = &it;
				return dynamic_cast<K>(*it);
			}
		}
		throw std::invalid_argument("Nic nenalezeno");
	}

	template<typename K>
	K& DejPosledni(size_t& index){
		for (UsporadanyKontejner::reverse_iterator it = this->rbegin(); it != this->rend(); ++it)
		{
			if (dynamic_cast<K>(*it) != NULL)
			{
				index = &it;
				return dynamic_cast<K>(*it);
			}
		}
		throw std::invalid_argument("Nic nenalezeno");
	}

	iterator begin(){
		return iterator(pole, 0);

	}
	iterator end(){
		return iterator(pole, pole.size()-1);
	}

	reverse_iterator rbegin(){
		return iterator(pole, pole.size()-1);
	}
	reverse_iterator rend(){
		return iterator(pole, 0);
	}
};

template<typename T>
struct DereferenceLess : public std::binary_function < T, T, bool > {
	bool operator()(T p1, T p2) {
		return *p1 < *p2;
	}
};


//////////////////// PARCIALNI SPECIALIZACE /////////////////////

template<typename T, typename Comparer>
class UsporadanyKontejner < T*, Comparer > {
	std::deque<T*> pole;
public:
	class iterator {
		size_t indexIteratoru;
		std::deque<T*> poleHodnot;
	public:
		iterator(std::deque<T*>& _poleHodnot, const size_t& _indexIteratoru) : poleHodnot(_poleHodnot), indexIteratoru(_indexIteratoru){}

		bool operator==(const iterator& iterator)const{
			return indexIteratoru == iterator.indexIteratoru;
		}
		bool operator!=(const iterator& iterator)const{
			return !(operator==(iterator));
		}
		iterator& operator[](size_t _indexIteratoru){
			indexIteratoru = _indexIteratoru;
			return *this;
		}
		iterator& operator++(){
			indexIteratoru++;
			return *this;
		}
		T* operator*() const{
			return poleHodnot[indexIteratoru];
		}
		size_t operator&()const{
			return indexIteratoru;
		}

	};

	class reverse_iterator{
		size_t indexIteratoru;
		std::deque<T*> poleHodnot;
	public:
		reverse_iterator(std::deque<T*>& _poleHodnot, const size_t& _indexIteratoru) : poleHodnot(_poleHodnot), indexIteratoru(_indexIteratoru){}

		bool operator==(const reverse_iterator& iterator)const{
			return indexIteratoru == iterator.indexIteratoru;
		}
		bool operator!=(const reverse_iterator& iterator)const{
			return !(operator==(iterator));
		}
		reverse_iterator& operator++() {
			indexIteratoru--;
			return *this;
		}
		reverse_iterator& operator[](size_t _indexIteratoru){
			indexIteratoru = _indexIteratoru;
			return *this;
		}
		T* operator*()const{
			return poleHodnot[indexIteratoru];
		}
		size_t operator&()const{
			return indexIteratoru;
		}

	};


	void Vloz(T* prvek) {
		Comparer compare;
		if (pole.empty()) {
			pole.push_front(prvek);
			return;
		}

		for (size_t i = 0; i < pole.size(); i++) {
			if (!(compare(pole.at(i), prvek))) {
				pole.insert(pole.begin() + i, prvek);
				return;
			}
		}
		pole.push_back(prvek);
	}
	void Odeber(iterator it){

		pole.erase(pole.begin() + &it);
	}


	T* operator[](size_t index){
		return pole[index];

	}

	const T* operator[](size_t index) const{
		return pole[index];
	}

	template<typename K>
	void Vykonej(void(*ukazatel)(K*))const{

		for (size_t i = 0; i < pole.size(); i++)
		{
			if (dynamic_cast<K*>(pole[i]) != NULL)
				ukazatel(dynamic_cast<K*>(pole[i]));
		}

	}
	template<typename K>
	K* DejPrvni(size_t& index){

		for (iterator it = this->begin(); it != this->end(); ++it)
		{
			if (dynamic_cast<K*>(*it) != NULL)
			{
				index = &it;
				return dynamic_cast<K*>(*it);
			}
		}
		
		throw std::invalid_argument("Nic nenalezeno");

		/*for (size_t i = index; i < pole.size(); i++)
		{
			if (dynamic_cast<K*>(pole[i]) != NULL)
			{
				index = i;
				return dynamic_cast<K*>(pole[i]);
			}

		}*/
		
	}

	template<typename K>
	K* DejPosledni(size_t& index){

		for (reverse_iterator it = this->rbegin(); it != this->rend(); ++it)
		{
			if (dynamic_cast<K*>(*it) != NULL)
			{
				index = &it;
				return dynamic_cast<K*>(*it);
			}
		}

		throw std::invalid_argument("Nic nenalezeno");

		/*for (size_t i = pole.size() - 1; i >= index; i--)
		{
			if (dynamic_cast<K*>(pole[i]) != NULL)
			{
				index = i;
				return dynamic_cast<K*>(pole[i]);
			}
		}*/
		
	}


	iterator begin(){
		return iterator(pole, 0);

	}
	iterator end() {
		return iterator(pole, pole.size()-1);
	}

	reverse_iterator rbegin(){
		return reverse_iterator(pole, pole.size()-1);
	}
	reverse_iterator rend(){
		return reverse_iterator(pole, 0);
	}
};





template<typename T>
struct DereferenceLess<T*> : public std::binary_function < T*, T*, bool > {
	bool operator()(T* p1, T* p2) {
		return *p1 < *p2;
	}
};


#endif // !USPORADANY_KONTEJNER_H

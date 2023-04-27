package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

//	Iterable<Ingredient> findAll(); // 반복문을 통해 식자재의 아이디, 이름 , 타입에접근
//	Ingredient findById(String id); // id값을 통해 식자재의 모든값에 접근
//	Ingredient save(Ingredient ingredient); //식자재값을 통해 식자재값을 저장
}

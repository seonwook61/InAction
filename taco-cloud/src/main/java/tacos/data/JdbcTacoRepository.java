package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {
	private JdbcTemplate jdbc;
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	// Taco테이블에 각 식자재를 저장하는 saveTacoInfo호출
	@Override
	public Taco save(Taco taco) {
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		for (Ingredient ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
		}
		return taco;
	}
	
	//각 식자재를 Taco테이블에 저장하는 메서드
	//반환값은 타코id
	
	private long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreator psc =
				new PreparedStatementCreatorFactory(
						"insert into Taco (name, createdAt) values (?, ?)",
						Types.VARCHAR, Types.TIMESTAMP
						).newPreparedStatementCreator(
								Arrays.asList(
										taco.getName(),
										new Timestamp(taco.getCreatedAt().getTime())));
		
		KeyHolder keyHolder = new GeneratedKeyHolder(); //생성된 타코Id값을 제공하는것이 KeyHolder
		jdbc.update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	//타코와 식자재의 연관정보를 저장하는 메서드
	private void saveIngredientToTaco(
			Ingredient ingredient, long tacoId) {
		jdbc.update(
				"insert into Taco_Ingredients (taco, ingredient) " +
						"values (?, ?)",
						tacoId, ingredient.getId());
	}
}
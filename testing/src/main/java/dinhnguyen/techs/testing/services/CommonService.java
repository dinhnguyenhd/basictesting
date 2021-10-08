package dinhnguyen.techs.testing.services;

import java.util.List;

public interface CommonService<F, E> {

	public List<E> list();

	public E getById(Long id);

	public E update(F form);

	public E insert(F form);

	public String deleteById(Long id);

	public List<E> complexSearch(F form);

}

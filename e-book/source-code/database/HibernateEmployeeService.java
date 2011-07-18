@Transactional
public class HibernateEmployeeService implements EmployeeService {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void delete(Long employeeId) {
		session().delete(get(employeeId));
	}

	@Override
	public Employee get(Long employeeId) {
		return (Employee) session().createCriteria(Employee.class).add(
				Restrictions.idEq(employeeId)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Employee> get() {
		return session().createCriteria(Employee.class).list();
	}

	@Override
	public Employee save(Employee employee) {
		session().saveOrUpdate(employee);
		return employee;
	}
}

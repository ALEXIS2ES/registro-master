package pe.edu.upeu.registro;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import pe.edu.upeu.registro.bean.Cars;
import pe.edu.upeu.registro.bean.Person;
import pe.edu.upeu.registro.dao.CarsDAO;
import pe.edu.upeu.registro.dao.PersonDAO;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("pe.edu.upeu.registro", appContext.getPackageName());

        PersonDAO dao = new PersonDAO(appContext);
        Person person = new Person();
        person.setName("Omar");
        person.setLastNameF("Perez");
        person.setLastNameM("Perez2");

        dao.savePerson(person);

        List<Person> list = dao.findPersonAll();

        Log.i("list:::",list.size()+"");

        dao.close();

        CarsDAO dao1= new CarsDAO(appContext);
        Cars cars= new Cars();
        cars.setCodProducto(80);
        cars.setDesProducto("Car");
        cars.setCantidad(132);
        cars.setPrecio(25);
        cars.setTotal(780);

        dao1.saveCars(cars);
        dao1.deleteCar(cars);
        dao1.updateCars(cars);
        dao1.deleteCars(cars);

        List<Cars> list1= dao1.findCarAll();

        Log.i("list:::",list1.size()+"");

        dao1.close();
    }
}

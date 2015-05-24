package vertx.handlers.http.api.test;


import io.vertx.test.core.HttpTestBase;
import org.junit.Test;
import vertx.handlers.http.api.controllers.Controller;
import vertx.handlers.http.api.routing.impl.RoutingContext;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static vertx.handlers.http.api.binding.ParameterBinder.createParameterBinder;
import static vertx.handlers.http.api.test.helper.RoutingContextBuilder.createRoutingContext;

public class ParameterBinderTests extends HttpTestBase {

    @Test
    public void testSimpleValueBinder() {
        Map<String, String> map = new HashMap<>();
        map.put("arg0", "foo");
        map.put("arg1", "bar");
        RoutingContext context = createRoutingContext().with(map)
                .with(TestController.class, TestController.class.getMethods()[0])
                .build();
        try {
            createParameterBinder().bind(context, parameters -> {
                assertEquals(2, parameters.length);
                assertEquals("foo", parameters[0]);
                assertEquals("bar", parameters[1]);
                testComplete();
            });
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail();
        }
    }

    public class TestController extends Controller {

        public String test1(String a, String b) {
            return String.format("%s %s", b, a);
        }
    }

}
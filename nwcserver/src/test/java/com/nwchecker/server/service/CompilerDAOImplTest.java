package test.java.com.nwchecker.server.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nwchecker.server.dao.CompilerDAO;
import com.nwchecker.server.model.Compiler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * <h1>CompilerDAOImpl Test</h1>
 * Test for CompilerDAOImpl method.
 * <p>
 *
 * @author Stanislav Krasovskyi
 * @version 1.0
 * @since 2015-03-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/forTests/context.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})

public class CompilerDAOImplTest {

    @Autowired
    private CompilerDAO compilerDAO;

    private List<Compiler> compilerList = new ArrayList<>();

    @Before
    public void init() {
        Compiler javaCompiler = new Compiler();
        javaCompiler.setId(1);
        javaCompiler.setName("Java");
        compilerList.add(javaCompiler);

        Compiler cSharpCompiler = new Compiler();
        cSharpCompiler.setId(2);
        cSharpCompiler.setName("C#");
        compilerList.add(cSharpCompiler);

        Compiler cPlusPlusCompiler = new Compiler();
        cPlusPlusCompiler.setId(3);
        cPlusPlusCompiler.setName("C++");
        compilerList.add(cPlusPlusCompiler);
    }

    @Test
    @DatabaseSetup("classpath:/forTests/dataset.xml")
    public void testGetAllCompilers() {
        List<Compiler> dbCompilersList = compilerDAO.getAllCompilers();
        assertEquals(compilerList.get(0), dbCompilersList.get(0));
        assertEquals(compilerList.get(1), dbCompilersList.get(1));
        assertEquals(compilerList.get(2), dbCompilersList.get(2));
    }
}

package org.jvargas.springcloud.msvc.cursos.services;

import org.jvargas.springcloud.msvc.cursos.clients.ClientesClientRest;
import org.jvargas.springcloud.msvc.cursos.models.Cliente;
import org.jvargas.springcloud.msvc.cursos.models.entity.Curso;
import org.jvargas.springcloud.msvc.cursos.models.entity.CursoCliente;
import org.jvargas.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServicempl implements CursoService {

    @Autowired
    private CursoRepository repository;
    @Autowired
    private ClientesClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Cliente> asignarCliente(Cliente cliente, Long cursoId) {
        Optional<Curso> o= repository.findById(cursoId);
        if (o.isPresent()){
            Cliente clienteMsvc= client.detalle(cliente.getId());//solo id
            Curso curso = o.get();
            CursoCliente cursoCliente = new CursoCliente();
            cursoCliente.setClienteId(clienteMsvc.getId());

            curso.addCursoUsuario(cursoCliente);
            repository.save(curso);
            return Optional.of(clienteMsvc);
        }


        return Optional.empty();
    }

    @Override
    public Optional<Cliente> crearCliente(Cliente cliente, Long cursoId) {
        Optional<Curso> o= repository.findById(cursoId);
        if (o.isPresent()){
            Cliente clienteNewMsvc= client.crear(cliente); //todo el usuario, cliente New MSVC-para nuevos
            Curso curso = o.get();
            CursoCliente cursoCliente = new CursoCliente();
            cursoCliente.setClienteId(clienteNewMsvc.getId());

            curso.addCursoUsuario(cursoCliente);
            repository.save(curso);
            return Optional.of(clienteNewMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Cliente> eliminarUsuario(Cliente cliente, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);
        if(o.isPresent()){
            Cliente usuarioMsvc = client.detalle(cliente.getId());
            Curso curso = o.get();

            CursoCliente cursoUsuario = new CursoCliente();
            cursoUsuario.setClienteId(usuarioMsvc.getId());
            curso.removeCursoUsuario((cursoUsuario));

            repository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porIdConUsuarios(Long id) {
        Optional<Curso> o = repository.findById(id);
        if(o.isPresent()){
            Curso curso = o.get();
            if(!curso.getCursoUsuarios().isEmpty()){
                List<Long> ids = curso.getCursoUsuarios().stream().map(cu -> cu.getClienteId())
                        .collect(Collectors.toList());
                List<Cliente> usuarios = client.obtenerAlumnosPorCurso(ids);
                curso.setUsuarios(usuarios);
            }
            return Optional.of(curso);
        }
        return Optional.empty();
    }
    @Override
    @Transactional
    public void eliminarCursoUsuarioPorId(Long id) {
        repository.eliminarCursoUsuarioPorId(id);
    }

}

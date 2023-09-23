package Model.BO;

import Exception.AutenticationException;
import Model.VO.UsuarioVO;

public interface UsuarioInterBO<VO extends UsuarioVO> extends BaseInterBO<VO>{
    public UsuarioVO autenticar (VO vo) throws AutenticationException;
}

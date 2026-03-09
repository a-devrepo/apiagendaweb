package br.com.nca.apiagendaweb.enums;

public enum Prioridade {
    ALTA(1),MEDIA(2),BAIXA(3);

    private Integer codigo;

    Prioridade(Integer codigo){
        this.codigo = codigo;
    }

    public static Prioridade  getPrioridade(Integer codigo){
        for(Prioridade prioridade: Prioridade.values()){
            if (prioridade.codigo.equals(codigo)){
                return prioridade;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida");
    }

    public Integer getCodigo() {
        return codigo;
    }
}

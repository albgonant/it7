<%@include file="index_header.jsp" %>
<div class="container">
    <div class="row">

        <div class="col-xs-12 datos-cohe">
            <h1 class="text-center">Agregar coche</h1>
            <hr/>
            <s:form class="form-horizontal" action="agregarCoche" method="POST">

                <div class="form-group">
                    <label class="control-label col-sm-2" for="marca">Marca:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="" name="marca" value="<s:property value="marca"></s:property>">
                        <s:fielderror fieldName="marca" cssClass="alert alert-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="modelo">Modelo:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="" name="modelo" value="<s:property value="modelo"></s:property>">
                        <s:fielderror fieldName="modelo" cssClass="alert alert-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="color">Color:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="" name="color" value="<s:property value="color"></s:property>">
                        <s:fielderror fieldName="color" cssClass="alert alert-danger" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="plazas">Plazas</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="" name="plazas" value="<s:property value="plazas"></s:property>">
                        <s:fielderror fieldName="plazas" cssClass="alert alert-danger" />
                    </div>
                </div>
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success btn-coche">Guardar</button>
                    </div>
                </div>

            </s:form>
        </div>
        <div class="col-lg-4">
        <s:form class="form-horizontal" action="toMisCoches" method="POST">
            <input type="submit" class="btn btn-info btn-coche" value="Volver"></input>
        </s:form>
            </div>
    </div>   

</div>

<%@include file="index_footer.jsp" %>
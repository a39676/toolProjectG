package job_test.rong360.request;

import java.util.HashMap;
import java.util.Map;

public class TianjiRequest{

    private Map<String, Object> params = new HashMap<String, Object>();

    public TianjiRequest putParam(String param, Object value) {
        this.params.put(param, value);
        return this;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

}
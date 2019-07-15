package com.wsy.webseed.util;

import com.alibaba.fastjson.JSON;
import com.wsy.webseed.common.exception.BussinessException;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import java.util.List;

/**
 * ES客户端
 *
 * @author wangsiyuan
 */
public class EsClient {

    private String name;

    private JestClientFactory factory = new JestClientFactory();

    /**
     * ES host:port
     *
     * @param serverList
     * @return
     */
    private void initJestClient(List<String> serverList) {
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder(serverList).multiThreaded(true).connTimeout(1000).readTimeout(30000)
                .maxTotalConnection(100).defaultMaxTotalConnectionPerRoute(10).build();
        factory.setHttpClientConfig(httpClientConfig);
    }

    public EsClient(String clusterName, List<String> clientHostPort) {
        try {
             initJestClient(clientHostPort);
            this.name = clusterName;
        } catch (Exception e) {
            throw new BussinessException("创建ES-JEST客户端失败,clusterName=" + clusterName + ",host=" + JSON.toJSONString(clientHostPort));
        }
    }

    public String getName() {
        return name;
    }

    public JestClient getClient() {
        return factory.getObject();
    }
}

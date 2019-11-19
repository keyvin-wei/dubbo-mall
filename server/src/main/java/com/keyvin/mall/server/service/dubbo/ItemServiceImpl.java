package com.keyvin.mall.server.service.dubbo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keyvin.mall.api.service.ItemService;
import com.keyvin.mall.common.config.BaseResponse;
import com.keyvin.mall.common.config.StatusCode;
import com.keyvin.mall.common.entity.ItemInfo;
import com.keyvin.mall.model.dao.ItemInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * @author weiwh
 * @date 2019/11/13 16:54
 */
@Path("${server.servlet.context-path}")
// @Service(protocol = {"dubbo"}, validation = "true", version = "1.0", timeout = 3000)
@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemInfoDao itemInfoDao;
    /**
     * 列表查询
     * @return
     */
    @Path("/listItem")
    @Override
    public BaseResponse listItem() {
        BaseResponse response = new BaseResponse(StatusCode.success);
        try {
            List<ItemInfo> infos = itemInfoDao.selectAll();
            log.info("查询到的商品列表数据：{}", infos);
            response.setData(infos);

        }catch (Exception e){
            e.printStackTrace();
            response = new BaseResponse(StatusCode.faild);
        }
        return response;
    }

    /**
     * 列表查询-带分页
     * @return
     */
    @Path("/listPageItem")
    @Override
    public BaseResponse listPageItem(@QueryParam("pageNo") Integer pageNo,
                                     @QueryParam("pageSize")Integer pageSize) {
        BaseResponse response = new BaseResponse(StatusCode.success);
        try {
            PageHelper.startPage(pageNo, pageSize);
            PageInfo<ItemInfo> pageInfo = new PageInfo(itemInfoDao.selectAll());
            response.setData(pageInfo);

        }catch (Exception e){
            e.printStackTrace();
            response = new BaseResponse(StatusCode.faild);
        }
        return response;
    }
}

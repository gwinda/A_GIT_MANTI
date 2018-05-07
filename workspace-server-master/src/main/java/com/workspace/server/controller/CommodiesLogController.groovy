package com.workspace.server.controller

import com.workspace.server.dao.CommoditiesEntityDao
import com.workspace.server.dao.CommoditiesLogEntityDao
import com.workspace.server.interceptor.ContentFormatInterceptor
import com.workspace.server.model.CommoditiesEntity
import com.workspace.server.model.CommoditiesLogEntity
import com.workspace.server.service.CommditiesEntityService
import com.workspace.server.service.CommditiesLogEntityService
import com.workspace.server.util.ContentFormatter
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CommodiesLogController {

    @Autowired CommoditiesLogEntityDao CommoditiesLogDao
    @Autowired CommditiesLogEntityService CommditiesLogService
    @Autowired CommoditiesEntityDao CommoditiesEntityDao
    @Autowired CommditiesEntityService CommditiesEntityService

    @ResponseBody
    @RequestMapping("/goodsLog/FindOneGoodPriceLog") //前端搜索功能Service ，返回json list ,在前端显示所有搜索数据(数据量多会分页)
    String getSearchResultViaAjax(@RequestBody CommoditiesLogEntity InputSearch, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        println InputSearch
        def cid = InputSearch.getCid()
        def jsonOutput = new JsonOutput()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        if(cid) {
            //判断链接是否已存在
            if (CommditiesLogService.exists(cid)) {
                def outputList = CommditiesLogService.findCommoditiesLogEntityBycid(cid)
                result = jsonOutput.toJson(outputList)
            } else {
                result = jsonOutput.toJson("{'result':'-1'}")

            }

        }

        return result.toString()//contentFormatter.toString()
    }

    @ResponseBody
    @RequestMapping("/goodsLog/CompareGoodsPriceLog") //前端搜索功能Service ，返回json list ,在前端显示所有搜索数据(数据量多会分页)
    String CompareGoodsPriceLogViaAjax(@RequestBody List<CommoditiesEntity> InputSearchList, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
        def jsonOutput = new JsonOutput()
        //搜索框传进来的数据可能是链接，也有可能是关键字，首先要进行判断是否为链接，链接则直接查询链接，不是链接的话就进行商品名称的模糊搜索
        def result =null
        def list = []
        def map =[:]
        //先遍历传进来的数据，获取每个对应的价格与CID 存到 map 中
        InputSearchList.each{ current_Comm->
            def cid = current_Comm?.getCid()
            def output = CommditiesEntityService.findCommoditiesEntityBycid(cid)
            map.put((int)output.getcLowestPrice(),cid)

        }
        //TreeMap是一种能够给Map对象的key排序的Map ,对上面已存的价格做排序，一会在前台会按价格低到高来拍折线，方便看懂.
        def tm = new TreeMap(map)
        //遍历map ，并取出所存的value,获取商品价格所有记录， 所有商品存于List中
        tm?.each{
            def cid= Integer.parseInt(""+it?.value)
            if(cid != 0) {
                //判断链接是否已存在
                if (CommditiesLogService.exists(cid)) {
                    def outputList = CommditiesLogService.findCommoditiesLogEntityBycid(cid)
                    println outputList
                    //对单个商品的记录按时间进行重新排序，按时间从小到大输出.
                    outputList.sort{a,b->
                        if (a.getClDateTime()< b.getClDateTime()) {
                            return -1
                        }else if (a.getClDateTime() == b.getClDateTime()) {
                            (a.getClId()).compareTo(b.getClId())
                        } else {
                            return 1
                        }
                    }
                    list.add(outputList)
                }
            }
        }
        //转成json 对象,返回给前台
        result = jsonOutput.toJson(list)
        return result.toString()//contentFormatter.toString()
    }


    //@ResponseBody
//    @RequestMapping("/goodsLog/DownloadGoodsResult") //将所有已经收藏或已经选择的商品导出
//    ResponseEntity<FileSystemResource> DownloadGoodsResultViaAjax(@RequestBody List<CommoditiesEntity> InputSearchList, @RequestAttribute(value = ContentFormatInterceptor.CONTENT_FORMATTER) ContentFormatter contentFormatter) {
//        //先遍历传进来的数据，获取每个对应的价格与CID 存到 map 中
//        List<CommoditiesEntity> list =new ArrayList<CommoditiesEntity>()
//        InputSearchList.each{ current_Comm->
//            def cid = current_Comm?.getCid()
//            def output = CommditiesEntityService.findCommoditiesEntityBycid(cid)
//            list.add(output)
//        }
//        def dir_file=writeCSV(list,"")
//        return  export(new File(dir_file))
//    }
//    //封装返回文件流的方法
//    ResponseEntity<InputStreamResource> export(File file) {
//        if (file == null) {
//            return null;
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".csv");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        headers.add("Last-Modified", new Date().toString());
//        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .body(new FileSystemResource(file));
//    }

    @RequestMapping('/goodsLog/DownloadGoodsResult')
    ResponseEntity<InputStreamResource> getSource (@RequestBody List<CommoditiesEntity> InputSearchList) {

        //先遍历传进来的数据，获取每个对应的价格与CID 存到 map 中
        List<CommoditiesEntity> list =new ArrayList<CommoditiesEntity>()
        InputSearchList.each{ current_Comm->
            def cid = current_Comm?.getCid()
            def output = CommditiesEntityService.findCommoditiesEntityBycid(cid)
            list.add(output)
        }

        File outputFile = new File(writeCSV(list,""))
        String filePath = outputFile.getAbsolutePath()
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", outputFile.getName()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    //save data to CSV File
    public String writeCSV(List<CommoditiesEntity> list,String dir) {
        def DIR = ''
        try {
            FileWriter fw =null
            List li=new ArrayList();
            def dd = GenRandomForTransID()
            println dd
            if(dir!=''){
                DIR = dir+"\\result-"+dd+".csv"
                fw =new FileWriter(dir+"\\result-"+dd+".csv");
            }else{
                DIR = "D:\\temp\\result-"+dd+".csv"
                fw =new FileWriter("D:\\temp\\result-"+dd+".csv");
            }
            String header = "商品ID,商品名成称,商品链接,商品价格,商品主图链接\r\n";
            fw.write(header);
            for (CommoditiesEntity Entity :list) {
                StringBuffer str = new StringBuffer();
                //if  after key not add  "\t" the Excel data will output as Scientific notation
                if(Entity!=null){
                    def cid =Entity.getCid()
                    def name=Entity.getcName()
                    def link=Entity.getcLink()
                    def price = Entity.getcLowestPrice()
                    def pic = Entity.getcPicture()
                    str.append(cid+","+name+","+link+","+price+","+pic+"\r\n");
                }
                fw.write(str.toString());
                fw.flush()
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DIR
    }
    public static String GenRandomForTransID() {
        try {
            int i = 1 + (int) (Math.random() * 1000);
            return String.valueOf(i);
        } catch (Exception ex) {
            return "000";
        }


    }
}

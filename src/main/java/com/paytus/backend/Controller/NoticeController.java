package com.paytus.backend.Controller;


import com.paytus.backend.dto.NoticeDTO;
import com.paytus.backend.model.response.CommonResult;
import com.paytus.backend.model.response.ListResult;
import com.paytus.backend.model.response.SingleResult;
import com.paytus.backend.service.NoticeService;
import com.paytus.backend.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Notice API"})
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    private NoticeService service;

    private final ResponseService responseService;
    public NoticeController(NoticeService service, ResponseService responseService){
        this.service = service;
        this.responseService = responseService;
    }

    @ApiOperation(value = "공지사항 게시글 전체 조회 api" ,notes = "자료실 전체 게시글을 조회합니다.")
    @GetMapping("")
    public ListResult<NoticeDTO> getAllNotice(){
        try {
            return responseService.getListResult(service.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "특정 공지사항 게시글 조회 api" ,notes = "해당 번호의 게시글을 조회합니다.")
    @GetMapping("/{noticeseq}")
    public SingleResult<NoticeDTO> getNotice(@PathVariable("noticeseq") int noticeseq){
        try {
            return responseService.getSingleResult(service.get(noticeseq));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "공지사항 게시글 생성 api" ,notes = "noticeseq=0을 넣으면 자동으로 번호가 생성되고 게시글을 생성합니다.")
    @PostMapping("")
    public SingleResult<NoticeDTO> registerNotice(@RequestBody NoticeDTO noticeDTO){
        if (noticeDTO.getNoticeseq()==0){
            try {
                service.register(noticeDTO);
                return responseService.getSingleResult(noticeDTO);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            return responseService.getFailSingleResult();

    }

    @ApiOperation(value = "공지사항 게시글 수정 api" ,notes = "해당 번호의 게시글을 수정합니다.")
    @PutMapping("/{noticeseq}")
    public SingleResult<NoticeDTO> modifyNotice(@PathVariable("noticeseq") int noticeseq, @RequestBody NoticeDTO noticeDTO){
        if (getNotice(noticeseq).getData()!=null){
            noticeDTO.setNoticeseq(noticeseq);
            try {
                service.modify(noticeDTO);
                return responseService.getSingleResult(noticeDTO);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            return responseService.getFailSingleResult();
    }

    @ApiOperation(value = "공지사항 게시글 삭제 api" ,notes = "해당 번호의 게시글을 삭제합니다.")
    @DeleteMapping("/{noticeseq}")
    public CommonResult removeNotice(@PathVariable("noticeseq") int noticeseq){
        if (getNotice(noticeseq).getData()!=null){
            try {
                service.remove(noticeseq);
                return responseService.getSuccessResult();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else
            return responseService.getFailResult();
    }
}
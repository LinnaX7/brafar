import {request} from "@/server/request";

export function getProblems(){
  return request({
    method: "get",
    url: "/problem/getProblems",
  })
}

export function getProblemById(id){
  return request({
    method: "post",
    url: "/problem/getProblemById",
    data:id
  })
}

export function getUploadFilesByProblemId(problemId){
  return request({
    method: "post",
    url: "/file/getUploadFilesByProblemId",
    data: problemId
  })
}

export function uploadFile(formData){
  return request({
    method: "post",
    url: "/file/upload",
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

export function refactorControlStructure(uploadFileId){
  return request({
    method: "post",
    url: "/refactor",
    data: uploadFileId
  })
}

export function createRepairRequest(uploadFileId){
  return request({
    method: "post",
    url: "/repair/createRequest",
    data: uploadFileId
  })
}

export function getRequests(){
  return request({
    method: "get",
    url: "/repair/getRequests",
  })
}


export function getRepairResults(requestId){
  return request({
    method: "post",
    url: "/repair/getResults",
    data: requestId
  })
}

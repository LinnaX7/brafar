<template>
  <div class="refactor-control-structure-graph-box" v-drag>
    <svg id="example" width="400" height="40" style="display: flex;">
      <g></g>
    </svg>
    <svg
        :id=controlStructureGraphId
        style="margin-top: 30px"
    >
      <defs>
        <marker id="markerStart" markerUnits="strokeWidth" markerWidth="6" markerHeight="4" refX="1" refY="2"
                orient="auto-start-reverse">
          <path d="M 0 0 L 6 2 L 0 4 L 1 2 z" fill="#103667"/>
        </marker>
        <marker id="markerEnd" markerUnits="strokeWidth" markerWidth="6" markerHeight="4" refX="1" refY="2"
                orient="auto">
          <path d="M 0 0 L 6 2 L 0 4 L 1 2 z" fill="#103667"/>
        </marker>
      </defs>
      <g></g>
    </svg>
  </div>
</template>

<script>
import * as d3 from "d3";
import dagreD3 from "dagre-d3";
export default {
  name: "RefactorControlStructure",
  props:{
    controlStructureGraphId:{
      type: String,
      required: true
    }
  },

  directives: {
    drag: {
      created: function (el) {
        el.onmousedown = function (ev) {
          const disX = ev.clientX
          const disY = ev.clientY
          const originalScrollLeft = el.scrollLeft
          const originalScrollTop = el.scrollTop
          const originalScrollBehavior = el.style['scroll-behavior']
          const originalPointerEvents = el.style['pointer-events']
          el.style['scroll-behavior'] = 'auto'
          el.style['cursor'] = 'grabbing'
          document.onmousemove = function (ev) {
            ev.preventDefault()
            const distanceX = ev.clientX - disX
            const distanceY = ev.clientY - disY
            el.scrollTo(originalScrollLeft - distanceX, originalScrollTop - distanceY)
            el.style['pointer-events'] = 'none'
            el.parentElement.style['cursor'] = 'grabbing'
          }
          document.onmouseup = function () {
            document.onmousemove = null
            document.onmouseup = null
            el.style['scroll-behavior'] = originalScrollBehavior
            el.style['pointer-events'] = originalPointerEvents
            el.style['cursor'] = 'grab'
          }
        }
      }
    }
  },

  data(){
    return {
      nodeStyle: {
        "common": "fill:white;stroke:black;stroke-width:1px;stroke-linecap:round;stroke-linejoin:round;",
        "controlStructure": "fill:#00994e;stroke:black;stroke-width:1px;stroke-linecap:round;stroke-linejoin:round;",
        "insert": "fill:#ff6600;stroke:black;stroke-width:1px;stroke-linecap:round;stroke-linejoin:round;",
        "move": "fill:#bf19ff;stroke:black;stroke-width:1px;stroke-linecap:round;stroke-linejoin:round;",
        "local": "fill:#0090ff;stroke:black;stroke-width:1px;stroke-linecap:round;stroke-linejoin:round;"
      },
      labelStyle: {
        "common": "fill: black;font-size: 12px;",
        "controlStructure": "fill: white;font-size: 12px;",
        "insert": "fill: white;font-size: 12px;",
        "move": "fill: white;font-size: 12px;",
        "local": "fill: white;font-size: 12px;",
      },
      example:[
        {
          id: "1",
          label: "主控制结构节点",
          style: "controlStructure",
        },
        {
          id: "2",
          label: "非主控制结构节点",
          style: "local",
        },
        {
          id: "3",
          label: "插入节点",
          style: "insert",
        },
        {
          id: "4",
          label: "移动节点",
          style: "move",
        },
      ]
    }
  },

  methods:{
    draw(){
      const g = new dagreD3.graphlib.Graph();
      g.setGraph({
        rankdir: 'TB',
        nodesep: 10
      })
      this.example.forEach(node => {
        g.setNode(node.id, {
          id: "e" + node.id,
          label: node.label,
          shape: "ellipse",
          style: this.nodeStyle[node.style],
          labelStyle: this.labelStyle[node.style],
        })
      })
      let render = new dagreD3.render()
      var svg = d3.select("#example")
      svg.select("g").remove()
      let svgGroup = svg.append('g')
      render(svgGroup, g)
    },

    drawRefactorGraph(graphInfo) {
      this.draw()
      const g = new dagreD3.graphlib.Graph();
      g.setGraph({
        rankdir: 'TB',
        marginx: 10,
        marginy: 10,
      });

      graphInfo.nodes.forEach(node => {
        g.setNode(node.id, {
          id: "n" + node.id,
          label: node.label,
          shape: "ellipse",
          style: this.nodeStyle[node.type],
          labelStyle: this.labelStyle[node.type],
        })
      })
      graphInfo.edges.forEach(edge => {
        g.setEdge(edge.source, edge.target, {
          style: "fill:none;stroke:black;stroke-width:1px;stroke-linejoin:round;",
          curve: d3.curveBasis,
          arrowhead:"vee",
        })
      })
      let render = new dagreD3.render()
      var svg = d3.select("#" + this.controlStructureGraphId)
      svg.select("g").remove()
      let svgGroup = svg.append('g')
      render(svgGroup, g)

      var height = document.querySelector("#" + this.controlStructureGraphId).getBBox().height
      var width = document.querySelector("#" + this.controlStructureGraphId).getBBox().width
      svg = document.getElementById(this.controlStructureGraphId)
      svg.setAttribute('height', height + 50)
      svg.setAttribute('width', width + 50)
    },

    drawMappingGraph(graphInfo) {
      this.draw()
      const g = new dagreD3.graphlib.Graph();
      g.setGraph({
        rankdir: 'TB',
        marginx: 10,
        marginy: 10,
      });

      graphInfo.nodes.forEach(node => {
        g.setNode(node.id, {
          id: "n" + node.id,
          label: node.label,
          shape: "ellipse",
          style: this.nodeStyle[node.matchType],
          labelStyle: this.labelStyle[node.matchType],
        })
      })
      graphInfo.edges.forEach(edge => {
        g.setEdge(edge.source, edge.target, {
          style: "fill:none;stroke:black;stroke-width:1px;stroke-linejoin:round;",
          curve: d3.curveBasis,
          arrowhead:"vee",
        })
      })
      let render = new dagreD3.render()
      var svg = d3.select("#" + this.controlStructureGraphId)
      svg.select("g").remove()
      let svgGroup = svg.append('g')
      render(svgGroup, g)
      var height = document.querySelector("#" + this.controlStructureGraphId).getBBox().height
      var width = document.querySelector("#" + this.controlStructureGraphId).getBBox().width
      svg = document.getElementById(this.controlStructureGraphId)
      svg.setAttribute('height', height + 50)
      svg.setAttribute('width', width + 50)
    },

    drawLegalMatchPath(matchNodes){
      var lines = []
      var svg = d3.select("#" + this.controlStructureGraphId)

      function setLine(source, target){
        var sourceTransform = source.node().getAttribute("transform")
        var targetTransform = target.node().getAttribute("transform")
        var sourceWidth = source.node().getBBox().width
        var targetWidth = target.node().getBBox().width
        line.source.x = Number(sourceTransform.substring(sourceTransform.indexOf("(") + 1, sourceTransform.indexOf(","))) + sourceWidth / 2
        line.source.y = Number(sourceTransform.substring(sourceTransform.indexOf(",") + 1, sourceTransform.indexOf(")")))
        line.target.x = Number(targetTransform.substring(targetTransform.indexOf("(") + 1, targetTransform.indexOf(","))) - targetWidth / 2
        line.target.y = Number(targetTransform.substring(targetTransform.indexOf(",") + 1, targetTransform.indexOf(")")))
        line.control.x = (line.source.x + line.target.x) / 2
        line.control.y = (line.source.y + line.target.y) / 2 + 60
      }
      for (let i = 0; i < matchNodes.length / 2; ++i){
        var line = {
          source: {},
          target: {},
          control: {}
        }
        var sourceNode = svg.select("#n" + matchNodes[i].source)
        var targetNode = svg.select("#n" + matchNodes[i].target)
        setLine(sourceNode, targetNode)
        lines.push(line)
      }

      lines.forEach(line => {
        var path = d3.path();
        path.moveTo(line.source.x, line.source.y)
        path.quadraticCurveTo(line.control.x, line.control.y, line.target.x, line.target.y);


        svg.select('g').append('path')
            .attr('d', path.toString())
            .style('fill','none')
            .style('stroke','#334f65')
            .style('stroke-width','2')
            .style('stroke-dasharray', '10,10')
            .style('marker-start', 'url(#markerStart)')
            .style('marker-end', 'url(#markerEnd)')
      })
    },

    drawLocalMatchPath(matchNodes){
      var lines = []
      var svg = d3.select("#" + this.controlStructureGraphId)

      function setLine(source, target){
        var sourceTransform = source.node().getAttribute("transform")
        var targetTransform = target.node().getAttribute("transform")
        var sourceWidth = source.node().getBBox().width
        var targetWidth = target.node().getBBox().width
        line.source.x = Number(sourceTransform.substring(sourceTransform.indexOf("(") + 1, sourceTransform.indexOf(","))) + sourceWidth / 2
        line.source.y = Number(sourceTransform.substring(sourceTransform.indexOf(",") + 1, sourceTransform.indexOf(")")))
        line.target.x = Number(targetTransform.substring(targetTransform.indexOf("(") + 1, targetTransform.indexOf(","))) - targetWidth / 2
        line.target.y = Number(targetTransform.substring(targetTransform.indexOf(",") + 1, targetTransform.indexOf(")")))
        line.control.x = (line.source.x + line.target.x) / 2
        line.control.y = (line.source.y + line.target.y) / 2 + 60
      }
      for (let i = 0; i < matchNodes.length / 2; ++i){
        var line = {
          source: {},
          target: {},
          control: {}
        }
        if (matchNodes[i].matchType === "local") {
          var sourceNode = svg.select("#n" + matchNodes[i].source)
          var targetNode = svg.select("#n" + matchNodes[i].target)
          setLine(sourceNode, targetNode)
          lines.push(line)
        }
      }

      lines.forEach(line => {
        var path = d3.path();
        path.moveTo(line.source.x, line.source.y)
        path.quadraticCurveTo(line.control.x, line.control.y, line.target.x, line.target.y);


        svg.select('g').append('path')
            .attr('d', path.toString())
            .style('fill','none')
            .style('stroke','#334f65')
            .style('stroke-width','2')
            .style('stroke-dasharray', '10,10')
            .style('marker-start', 'url(#markerStart)')
            .style('marker-end', 'url(#markerEnd)')
      })
    }


  }
}
</script>

<style scoped>

.refactor-control-structure-graph-box{
  height: 90%;
  width: 95%;
  margin-top: 15px;
  margin-left: 30px;
  border-radius: 21px;
  box-shadow: 0px 1px 2px rgba(0,0,0, 0.1),0px 10px 22px rgba(0,0,0, 0.16);
  border: 1px solid rgba(229,229,229, 1);
  background: white;
  overflow: auto;
}

::-webkit-scrollbar {
  -webkit-appearance: none;
  width: 5px;
  height: 8px;
}

::-webkit-scrollbar-thumb {
  cursor: pointer;
  border-radius: 10px;
  background: rgba(0, 0, 0, 0.15);
  transition: color 0.2s ease;
}

</style>
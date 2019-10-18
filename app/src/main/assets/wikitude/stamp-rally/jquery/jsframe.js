! function (e, t) {
  if ("object" == typeof exports && "object" == typeof module) module.exports = t();
  else if ("function" == typeof define && define.amd) define([], t);
  else {
    var r = t();
    for (var o in r)("object" == typeof exports ? exports : e)[o] = r[o]
  }
}(window, function () {
  return function (r) {
    var o = {};

    function n(e) {
      if (o[e]) return o[e].exports;
      var t = o[e] = {
        i: e,
        l: !1,
        exports: {}
      };
      return r[e].call(t.exports, t, t.exports, n), t.l = !0, t.exports
    }
    return n.m = r, n.c = o, n.d = function (e, t, r) {
      n.o(e, t) || Object.defineProperty(e, t, {
        enumerable: !0,
        get: r
      })
    }, n.r = function (e) {
      "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {
        value: "Module"
      }), Object.defineProperty(e, "__esModule", {
        value: !0
      })
    }, n.t = function (t, e) {
      if (1 & e && (t = n(t)), 8 & e) return t;
      if (4 & e && "object" == typeof t && t && t.__esModule) return t;
      var r = Object.create(null);
      if (n.r(r), Object.defineProperty(r, "default", {
          enumerable: !0,
          value: t
        }), 2 & e && "string" != typeof t)
        for (var o in t) n.d(r, o, function (e) {
          return t[e]
        }.bind(null, o));
      return r
    }, n.n = function (e) {
      var t = e && e.__esModule ? function () {
        return e.default
      } : function () {
        return e
      };
      return n.d(t, "a", t), t
    }, n.o = function (e, t) {
      return Object.prototype.hasOwnProperty.call(e, t)
    }, n.p = "", n(n.s = 4)
  }([function (e, t, r) {
    "use strict";
    e.exports = function (r) {
      var i = [];
      return i.toString = function () {
        return this.map(function (e) {
          var t = function (e, t) {
            var r = e[1] || "",
              o = e[3];
            if (!o) return r;
            if (t && "function" == typeof btoa) {
              var n = (i = o, "/*# sourceMappingURL=data:application/json;charset=utf-8;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(i)))) + " */"),
                a = o.sources.map(function (e) {
                  return "/*# sourceURL=" + o.sourceRoot + e + " */"
                });
              return [r].concat(a).concat([n]).join("\n")
            }
            var i;
            return [r].join("\n")
          }(e, r);
          return e[2] ? "@media " + e[2] + "{" + t + "}" : t
        }).join("")
      }, i.i = function (e, t) {
        "string" == typeof e && (e = [
          [null, e, ""]
        ]);
        for (var r = {}, o = 0; o < this.length; o++) {
          var n = this[o][0];
          null != n && (r[n] = !0)
        }
        for (o = 0; o < e.length; o++) {
          var a = e[o];
          null != a[0] && r[a[0]] || (t && !a[2] ? a[2] = t : t && (a[2] = "(" + a[2] + ") and (" + t + ")"), i.push(a))
        }
      }, i
    }
  }, function (e, t, o) {
    var r, n, a, s = {},
      d = (r = function () {
        return window && document && document.all && !window.atob
      }, function () {
        return void 0 === n && (n = r.apply(this, arguments)), n
      }),
      i = (a = {}, function (e, t) {
        if ("function" == typeof e) return e();
        if (void 0 === a[e]) {
          var r = function (e, t) {
            return t ? t.querySelector(e) : document.querySelector(e)
          }.call(this, e, t);
          if (window.HTMLIFrameElement && r instanceof window.HTMLIFrameElement) try {
            r = r.contentDocument.head
          } catch (e) {
            r = null
          }
          a[e] = r
        }
        return a[e]
      }),
      u = null,
      p = 0,
      l = [],
      m = o(7);

    function c(e, t) {
      for (var r = 0; r < e.length; r++) {
        var o = e[r],
          n = s[o.id];
        if (n) {
          n.refs++;
          for (var a = 0; a < n.parts.length; a++) n.parts[a](o.parts[a]);
          for (; a < o.parts.length; a++) n.parts.push(C(o.parts[a], t))
        } else {
          var i = [];
          for (a = 0; a < o.parts.length; a++) i.push(C(o.parts[a], t));
          s[o.id] = {
            id: o.id,
            refs: 1,
            parts: i
          }
        }
      }
    }

    function h(e, t) {
      for (var r = [], o = {}, n = 0; n < e.length; n++) {
        var a = e[n],
          i = t.base ? a[0] + t.base : a[0],
          l = {
            css: a[1],
            media: a[2],
            sourceMap: a[3]
          };
        o[i] ? o[i].parts.push(l) : r.push(o[i] = {
          id: i,
          parts: [l]
        })
      }
      return r
    }

    function f(e, t) {
      var r = i(e.insertInto);
      if (!r) throw new Error("Couldn't find a style target. This probably means that the value for the 'insertInto' parameter is invalid.");
      var o = l[l.length - 1];
      if ("top" === e.insertAt) o ? o.nextSibling ? r.insertBefore(t, o.nextSibling) : r.appendChild(t) : r.insertBefore(t, r.firstChild), l.push(t);
      else if ("bottom" === e.insertAt) r.appendChild(t);
      else {
        if ("object" != typeof e.insertAt || !e.insertAt.before) throw new Error("[Style Loader]\n\n Invalid value for parameter 'insertAt' ('options.insertAt') found.\n Must be 'top', 'bottom', or Object.\n (https://github.com/webpack-contrib/style-loader#insertat)\n");
        var n = i(e.insertAt.before, r);
        r.insertBefore(t, n)
      }
    }

    function y(e) {
      if (null === e.parentNode) return !1;
      e.parentNode.removeChild(e);
      var t = l.indexOf(e);
      0 <= t && l.splice(t, 1)
    }

    function b(e) {
      var t = document.createElement("style");
      if (void 0 === e.attrs.type && (e.attrs.type = "text/css"), void 0 === e.attrs.nonce) {
        var r = function () {
          0;
          return o.nc
        }();
        r && (e.attrs.nonce = r)
      }
      return g(t, e.attrs), f(e, t), t
    }

    function g(t, r) {
      Object.keys(r).forEach(function (e) {
        t.setAttribute(e, r[e])
      })
    }

    function C(t, e) {
      var r, o, n, a, i, l;
      if (e.transform && t.css) {
        if (!(a = "function" == typeof e.transform ? e.transform(t.css) : e.transform.default(t.css))) return function () {};
        t.css = a
      }
      if (e.singleton) {
        var s = p++;
        r = u || (u = b(e)), o = E.bind(null, r, s, !1), n = E.bind(null, r, s, !0)
      } else n = t.sourceMap && "function" == typeof URL && "function" == typeof URL.createObjectURL && "function" == typeof URL.revokeObjectURL && "function" == typeof Blob && "function" == typeof btoa ? (i = e, l = document.createElement("link"), void 0 === i.attrs.type && (i.attrs.type = "text/css"), i.attrs.rel = "stylesheet", g(l, i.attrs), f(i, l), o = function (e, t, r) {
        var o = r.css,
          n = r.sourceMap,
          a = void 0 === t.convertToAbsoluteUrls && n;
        (t.convertToAbsoluteUrls || a) && (o = m(o));
        n && (o += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(n)))) + " */");
        var i = new Blob([o], {
            type: "text/css"
          }),
          l = e.href;
        e.href = URL.createObjectURL(i), l && URL.revokeObjectURL(l)
      }.bind(null, r = l, e), function () {
        y(r), r.href && URL.revokeObjectURL(r.href)
      }) : (r = b(e), o = function (e, t) {
        var r = t.css,
          o = t.media;
        o && e.setAttribute("media", o);
        if (e.styleSheet) e.styleSheet.cssText = r;
        else {
          for (; e.firstChild;) e.removeChild(e.firstChild);
          e.appendChild(document.createTextNode(r))
        }
      }.bind(null, r), function () {
        y(r)
      });
      return o(t),
        function (e) {
          if (e) {
            if (e.css === t.css && e.media === t.media && e.sourceMap === t.sourceMap) return;
            o(t = e)
          } else n()
        }
    }
    e.exports = function (e, i) {
      if ("undefined" != typeof DEBUG && DEBUG && "object" != typeof document) throw new Error("The style-loader cannot be used in a non-browser environment");
      (i = i || {}).attrs = "object" == typeof i.attrs ? i.attrs : {}, i.singleton || "boolean" == typeof i.singleton || (i.singleton = d()), i.insertInto || (i.insertInto = "head"), i.insertAt || (i.insertAt = "bottom");
      var l = h(e, i);
      return c(l, i),
        function (e) {
          for (var t = [], r = 0; r < l.length; r++) {
            var o = l[r];
            (n = s[o.id]).refs--, t.push(n)
          }
          e && c(h(e, i), i);
          for (r = 0; r < t.length; r++) {
            var n;
            if (0 === (n = t[r]).refs) {
              for (var a = 0; a < n.parts.length; a++) n.parts[a]();
              delete s[n.id]
            }
          }
        }
    };
    var v, B = (v = [], function (e, t) {
      return v[e] = t, v.filter(Boolean).join("\n")
    });

    function E(e, t, r, o) {
      var n = r ? "" : o.css;
      if (e.styleSheet) e.styleSheet.cssText = B(t, n);
      else {
        var a = document.createTextNode(n),
          i = e.childNodes;
        i[t] && e.removeChild(i[t]), i.length ? e.insertBefore(a, i[t]) : e.appendChild(a)
      }
    }
  }, function (e, t, r) {
    var a = r(9);

    function o() {
      this.fps = 30, this.durationMillis = 200, this.targetFrame = null, this._crrAlpha = 1, this._toAlpha = 1, this._crrWidth = 0, this._crrHeight = 0, this._toWidth = 0, this._toHeight = 0, this._toX = 0, this._toY = 0, this.pinnedAnimationEnabled = !1, this._pinX = 0, this._pinY = 0, this._pinAnchor = null
    }
    o.prototype.set = function (e) {
      var t = this;
      t.targetFrame = e, t.fromWidth(e.getWidth()), t.fromHeight(e.getHeight()), t.toWidth(e.getWidth()), t.toHeight(e.getHeight());
      var r = e.getPosition();
      return t.fromPosition(r.x, r.y, r.anchor), t
    }, o.prototype.get = function () {
      return this.targetFrame
    }, o.prototype.setDuration = function (e) {
      return this.durationMillis = e, this
    }, o.prototype.setFPS = function (e) {
      return this.fps = e, this
    }, o.prototype.fromPosition = function (e, t, r) {
      var o = this;
      return o.pinnedAnimationEnabled = !0, o._pinX = e, o._pinY = t, o.toPosition(e, t), r && (o._pinAnchor = r), o
    }, o.prototype.fromWidth = function (e) {
      return this._crrWidth = e, this
    }, o.prototype.fromHeight = function (e) {
      return this._crrHeight = e, this
    }, o.prototype.toWidth = function (e) {
      return this._toWidth = e, this
    }, o.prototype.toHeight = function (e) {
      return this._toHeight = e, this
    }, o.prototype.fromAlpha = function (e) {
      return this._crrAlpha = e, this
    }, o.prototype.toAlpha = function (e) {
      return this._toAlpha = e, this
    }, o.prototype.toPosition = function (e, t) {
      return this._toX = e, this._toY = t, this
    }, o.prototype.toX = function (e) {
      return this._toX = e, this
    }, o.prototype.toY = function (e) {
      return this._toY = e, this
    }, o.prototype.start = function (n, h) {
      var f = this,
        y = f._crrWidth,
        b = f._crrHeight,
        g = f._pinX,
        C = f._pinY,
        v = f._crrAlpha;
      return new Promise(function (i, e) {
        var l = parseInt(f.fps * (f.durationMillis / 1e3));
        0 == l && (l = 1);
        var s = (f._toWidth - y) / l,
          d = (f._toHeight - b) / l,
          u = (f._toX - g) / l,
          p = (f._toY - C) / l,
          m = (f._toAlpha - v) / l,
          t = f.durationMillis / l,
          c = 0;

        function r() {
          var e = new a;
          e.setIntervalMillis(t), e.setCallback(function (e) {
            if (c == l) {
              var t = f._toWidth,
                r = f._toHeight,
                o = g + u * c,
                n = C + p * c,
                a = f._toAlpha;
              f.pinnedAnimationEnabled && f.targetFrame._setPositionInternally(o, n, f._pinAnchor, t, r), f.targetFrame.htmlElement.style && (f.targetFrame.htmlElement.style.opacity = a), f.targetFrame.setSize(t, r, !0), f._crrWidth = t, f._crrHeight = r, f._pinX = o, f._pinY = n
            }
            if (c == l + 1) return e.stopTimer(), void i(f);
            t = y + s * c, r = b + d * c, o = g + u * c, n = C + p * c, a = v + m * c;
            (f.pinnedAnimationEnabled && f.targetFrame._setPositionInternally(o, n, f._pinAnchor, t, r), f.targetFrame.htmlElement.style && (f.targetFrame.htmlElement.style.opacity = a), f.targetFrame.setSize(t, r, !0), 0 == c) && (f.targetFrame.parentCanvas.getWindow(f.targetFrame.id) && f.targetFrame.show({
              requestFocus: h
            }));
            c++
          }), e.startTimer()
        }
        if (n) {
          var o = new a;
          o.setIntervalMillis(n), o.setCallback(function (e) {
            e.stopTimer(), r()
          }), o.startTimer()
        } else r()
      })
    }, e.exports = o
  }, function (e, t) {
    CALIGN = {
      LEFT_TOP: "LEFT_TOP",
      HCENTER_TOP: "CENTER_TOP",
      RIGHT_TOP: "RIGHT_TOP",
      LEFT_VCENTER: "LEFT_CENTER",
      HCENTER_VCENTER: "CENTER_CENTER"
    }, CALIGN.CENTER = CALIGN.HCENTER_VCENTER, CALIGN.RIGHT_VCENTER = "RIGHT_CENTER", CALIGN.LEFT_BOTTOM = "LEFT_BOTTOM", CALIGN.HCENTER_BOTTOM = "CENTER_BOTTOM", CALIGN.RIGHT_BOTTOM = "RIGHT_BOTTOM", e.exports = CALIGN
  }, function (e, t, r) {
    "use strict";
    r.r(t), r.d(t, "WindowEventHelper", function () {
      return o
    }), r.d(t, "JSFrame", function () {
      return u
    }), r(5);
    var o = r(8),
      E = r(3),
      n = r(2),
      a = {
        yosemite: r(10),
        redstone: r(13),
        popup: r(16),
        toast: r(19),
        material: r(20)
      },
      x = {};

    function i() {
      var o = this;
      this.showTitleBar = !0, this.showCloseButton = !0, this.titleBarCaption = "", this.titleBarCaptionFontSize = "12px", this.titleBarCaptionFontWeight = "bold", this.titleBarHeight = "24px", this.useIframe = !1, this.useFrame = !0, this.setUseIFrame = function (e) {
        return o.useIframe = e, o.useFrame = !e, o
      }, this.titleBarCaptionLeftMargin = "5px", this.titleBarColorDefault = "lightgray", this.titleBarColorFocused = "#d3e1ee", this.titleBarCaptionColorDefault = "", this.titleBarCaptionColorFocused = "", this.titleBarCaptionTextShadow = "0 1px 0 rgba(255,255,255,.7)", this.titleBarCaptionTextAlign = "center", this.titleBarBorderBottomDefault = "1px solid rgba(0,0,0,0.2)", this.titleBarBorderBottomFocused = null, this.frameBorderRadius = "6px", this.frameBorderWidthDefault = "1px", this.frameBorderWidthFocused = this.frameBorderWidthDefault, this.frameBorderColorDefault = "rgba(1, 1, 1, 0.2)", this.frameBorderColorFocused = this.frameBorderColorDefault, this.frameBorderStyle = "solid", this.frameBoxShadow = "2px 3px 16px rgba(0,0,0,.6)", this.frameBackgroundColor = "transparent", this._partsBuilder = null, this.frameComponents = [], this.frameHeightAdjust = 1, this.getFrameInnerBorderRadius = function (e, t) {
        if (e) return t ? parseInt(e.frameBorderRadius, 10) - parseInt(e.frameBorderWidthFocused, 10) + "px" : parseInt(e.frameBorderRadius, 10) - parseInt(e.frameBorderWidthDefault, 10) + "px"
      }, this.onInitialize = function () {
        if (o.showCloseButton) {
          var e = o.getPartsBuilder(),
            t = e.buildTextButtonAppearance();
          t.size = 14, t.captionShiftYpx = 0, t.captionFontRatio = 1, t.borderRadius = 2, t.backgroundColorPressed = "transparent", t.backgroundColorDefault = "transparent", t.caption = "✖", t.captionColorDefault = "gray", t.captionColorFocused = "gray", t.captionColorHovered = "silver", t.captionColorPressed = "black", t.borderWidth = 0, t.borderColorDefault = "#aaaaaa", t.borderStyle = "solid";
          var r = e.buildTextButton(t);
          o.addFrameComponent("closeButton", r, -10, -18, "RIGHT_TOP")
        }
      }, this.onTitleBarStyleInitialize = function () {}
    }

    function l(e, t) {
      function r() {}
      r.prototype = t.prototype, e.prototype = new r, (e.prototype.constructor = e).superConstructor = t, e.superClass = t.prototype
    }

    function s(e, t, r, o, n, a, i, l) {
      var s = this;
      s.movable = !0, s.id = e, s.property = {}, s.extra = {}, s.parentCanvas = null, s.htmlElement = null, s.pullUpDisabled = !1, l && (s.pullUpDisabled = l.pullUpDisabled), s.htmlElement = document.createElement(x.CBEAN.HTML_ELEMENT), s.htmlElement.id = x.CBEAN.HTML_ELEMENT_ID_PREFIX + e, s.htmlElement.style.position = "absolute", s.htmlElement.style.left = parseInt(t, 10) + "px", s.htmlElement.style.top = parseInt(r, 10) + "px", s.htmlElement.style.width = parseInt(o, 10) + "px", s.htmlElement.style.height = parseInt(n, 10) + "px", null !== a && (s.htmlElement.style.zIndex = a), s.htmlElement.style.borderColor = "#000000", s.htmlElement.style.fontSize = "1px", (s.htmlElement.parent = s).htmlElement.onmousedown = s.onmouseDown, s.htmlElement.typeName = x.CBEAN.TYPE_NAME, s.htmlElement.usage = "nothing", s.htmlElement.isRangeLimited = !1, s.htmlElement.argX = 1, s.htmlElement.argY = 1, s.externalAreaClickedListener = null
    }

    function w(e, t, r, o, n, a) {
      var i = this;
      i.currentObject = null, i.onTopObject = null, i.offsetX = 0, i.offsetY = 0, i.mouseDownSource = null, i.id = t, i.canvasElement = null, i.parentElement = e, i.beanList = new Array, i.beanIdName = {}, i.beanNameId = {}, i.eventData = new function () {
        this.x = 0, this.y = 0, this.screenX = 0, this.screenY = 0, this.deltaX = 0, this.deltaY = 0, this.isMoved = !1, this.targetTypeName = null, this.targetUsage = null, this.targetObject = null
      }, i.baseZIndex = 0, i.setBaseZIndex = function (e) {
        i.baseZIndex = e
      }, i.getBaseZIndex = function () {
        return i.baseZIndex
      }, i.canvasElement = document.createElement(x.CANVAS.HTML_ELEMENT), i.canvasElement.style.zIndex = 2e3, i.canvasElement.id = i.id, i.canvasElement.style.boxSizing = "border-box", i.canvasElement.style.position = "absolute", i.canvasElement.style.left = parseInt(r) + "px", i.canvasElement.style.top = parseInt(o) + "px", i.canvasElement.style.width = parseInt(n) + x.CANVAS.WIDTH_ADJUST_20180722 + "px", i.canvasElement.style.height = parseInt(a) + x.CANVAS.HEIGHT_ADJUST_20180722 + "px", i.canvasElement.style.backgroundColor = "transparent", i.canvasElement.style.borderStyle = "none", i.canvasElement.style.margin = "0px", i.canvasElement.style.borderWidth = "0px", i.canvasElement.style.borderColor = "transparent", i.parentElement.appendChild(i.canvasElement)
    }

    function F(e, t, r, o, n, a, i, l) {
      var s = this;
      F.superConstructor.call(this, e, t, r, o, n, a, i, l), s.anchor = E.LEFT_TOP, s.showTitleBar = l.showTitleBar, s.canvasNetHeight = null, s.canvasNetWidth = null, s.frameHeightAdjust = l.frameHeightAdjust, s.frameComponentMap = {}, s.canvas = null, s.myCanvasId = null, s.floatingButton = null, s.titleBarClassNameDefault = "jsframe-titlebar-default", s.titleBarClassNameFocused = "jsframe-titlebar-focused", s.titleBarHeight = l.titleBarHeight, s.titleBarCaption = l.titleBarCaption, s.titleBarCaptionLeftMargin = l.titleBarCaptionLeftMargin, s.titleBarCaptionFontSize = l.titleBarCaptionFontSize, s.titleBarCaptionFontWeight = l.titleBarCaptionFontWeight, s.titleBarBorderBottomDefault = l.titleBarBorderBottomDefault, s.titleBarBorderBottomFocused = l.titleBarBorderBottomFocused, s.titleBarCaptionTextShadow = l.titleBarCaptionTextShadow, s.titleBarCaptionTextAlign = l.titleBarCaptionTextAlign, s.titleAdjustWidth = 2, s.windowId = e, s.exBorderWidth = 0, s.myCanvasId = e + "_canvas";
      var d = document.createElement("img");
      if (d.src = "", d.style.position = "absolute", d.style.left = "2px", d.style.top = "2px", d.style.width = "16px", d.style.height = "16px", d.style.visibility = "hidden", s.titleBar = document.createElement("div"), s.titleBar.className = "jsframetitlebar", s.showTitleBar) {
        if (s.titleBar.id = e + "_title", s.titleBar.style.position = "absolute", s.titleBar.style.boxSizing = "border-box", s.titleBar.style.top = "0px", s.titleBar.style.left = "0px", s.titleBar.style.width = o - s.titleAdjustWidth + x.CANVAS.WIDTH_ADJUST_20180722 + "px", s.titleBar.style.userSelect = "none", s.titleBarHeight) {
          var u = 0;
          s.titleBarBorderBottomDefault && (u = 0), s.titleBar.style.height = parseInt(s.titleBarHeight, 10) + 0 + "px"
        }
        s.titleBar.style.backgroundColor = s.titleBarColorDefault, s.titleBar.style.zIndex = 0, s.titleBar.style.color = s.titleBarCaptionColorDefault, s.titleBar.style.fontSize = s.titleBarCaptionFontSize, s.titleBar.style.fontWeight = s.titleBarCaptionFontWeight, s.titleBar.style.textShadow = s.titleBarCaptionTextShadow, s.titleBar.style.textAlign = s.titleBarCaptionTextAlign, s.titleBar.style.lineHeight = s.titleBar.style.height, s.titleBar.style.borderBottom = s.titleBarBorderBottomDefault, s.titleBar.style.overflow = "hidden";
        var p = document.createTextNode(""),
          m = document.createElement("span");
        m.id = s.id + "_titleBarText", null != s.titleBarCaptionLeftMargin ? (m.style.position = "absolute", m.style.left = parseInt(s.titleBarCaptionLeftMargin, 10) + "px") : (m.style.position = "absolute", m.style.left = "0px", m.style.right = "0px"), m.style.top = "0px", m.appendChild(p), s.titleBar.appendChild(m)
      }
      s.htmlElement.appendChild(s.titleBar);
      var c = parseInt(s.titleBarHeight, 10) - u,
        h = parseInt(s.titleAdjustWidth, 10);
      s.showTitleBar || (u = h = c = 0), s.canvasNetWidth = o - h, s.canvasNetHeight = n - c - h - 1 - u + s.frameHeightAdjust, s.htmlElement.style.cursor = "move", s.canvas = new w(s.htmlElement, s.myCanvasId, 0, c, o - h, n - c - h), s.canvas.canvasElement.style.backgroundColor = x.CFRAME.CANVAS_ELEMENT_BGCOLOR, s.canvas.canvasElement.style.cursor = "default", s.canvas.canvasElement.onmousedown = s.canvasMouseDown, s.canvas.canvasElement.parentCFrame = s;
      var f = parseInt(s.canvas.canvasElement.style.width, 10),
        y = parseInt(s.canvas.canvasElement.style.height, 10),
        b = new T(s.myCanvasId + "_RD", f - 16 + 16, y - 16 + 16, 16, 16, 0, "RD");
      b.htmlElement.style.cursor = "se-resize", b.htmlElement.argX = 0, b.htmlElement.argY = 0;
      var g = new T(s.myCanvasId + "_DD", 0, y - 16 + 16, f - 16 + 16, 16, 0, "DD");
      g.htmlElement.style.cursor = "n-resize", g.htmlElement.argX = 0, g.htmlElement.argY = 0;
      var C = new T(s.myCanvasId + "_RR", f - 16 + 16, 0, 16, y - 16 + 16, 0, "RR");
      for (var v in C.htmlElement.style.cursor = "w-resize", C.htmlElement.argY = 0, C.htmlElement.argX = 0, s.canvas.addBean(b), s.canvas.addBean(g), s.canvas.addBean(C), s.removeMarkers = function () {
          s.canvas.removeBean(b.id), s.canvas.removeBean(g.id), s.canvas.removeBean(C.id), s.htmlElement.style.cursor = "default"
        }, s.removeMarkers2 = function () {
          s.canvas.removeBean(b.id), s.canvas.removeBean(g.id), s.canvas.removeBean(C.id)
        }, s.enableMarkers = function (e) {
          e ? (b.htmlElement.style.display = "flex", g.htmlElement.style.display = "flex", C.htmlElement.style.display = "flex", b.htmlElement.style.cursor = "se-resize", g.htmlElement.style.cursor = "n-resize", C.htmlElement.style.cursor = "w-resize") : (b.htmlElement.style.display = "none", g.htmlElement.style.display = "none", C.htmlElement.style.display = "none")
        }, l.frameComponents) {
        var B = l.frameComponents[v];
        B.setFrame(s), "closeButton" == B.id && (B.htmlElement.onclick = s.close), s.addFrameComponent(B)
      }
      s.htmlElement.style.backgroundColor = "transparent", s.htmlElement.oncontextmenu = this.contextMenu;
      s.caribValue = 0, s.htmlElement.style.borderWidth = s.exBorderWidth + "px", s.htmlElement.style.width = parseInt(s.htmlElement.style.width, 10) - 0 + "px", s.htmlElement.style.height = parseInt(s.htmlElement.style.height, 10) - 0 + 1 + "px", s.htmlElement.typeName = "cwindow", s.htmlElement.overflow = "auto", s.htmlElement.style.boxSizing = "content-box", l.frameBorderStyle && (s.htmlElement.style.borderStyle = l.frameBorderStyle), l.frameBoxShadow && (s.htmlElement.style.boxShadow = l.frameBoxShadow), 0 < parseInt(l.frameBorderWidthDefault, 10) && (s.htmlElement.style.borderWidth = l.frameBorderWidthDefault, s.htmlElement.style.borderColor = l.frameBorderColorDefault), 0 < parseInt(l.frameBorderRadius, 10) && (s.htmlElement.style.borderRadius = l.frameBorderRadius), s.onCloseFrameListener = null
    }

    function c(e, t, r, o, n, a) {
      var i = t,
        l = r,
        s = o,
        d = n,
        u = a.zindex,
        p = this;
      this.jsFrame = null, this.control = null, this.minFrameWidth = 128, this.minWindowHeight = 32, this.overrayTransparentScreenEnabled = !1, this.overrayTransparentScreenOnResize = !0, this.titleBarColorFocused = a.titleBarColorFocused, this.titleBarColorDefault = a.titleBarColorDefault, this.titleBarCaptionColorDefault = a.titleBarCaptionColorDefault, this.titleBarCaptionColorFocused = a.titleBarCaptionColorFocused, c.superConstructor.call(p, e, i, l, s, d, u, null, a), p.frameBorderColorDefault = a.frameBorderColorDefault, p.frameBorderColorFocused = a.frameBorderColorFocused, p.frameBorderWidthDefault = a.frameBorderWidthDefault, p.frameBorderWidthFocused = a.frameBorderWidthFocused, p.iframe = null, p.ifDelta = 0, p.resizable = !0, p.onMouseMoveOnIframe = null, p.onMouseUpOnIframe = null, p._hasFocus = !1, p._hasFocusTime = 0, p.htmlElement.typeName = "cifwindow";
      var m = "riversun_" + e;
      p.dframe = document.createElement("div"), p.iframe = document.createElement("iframe"), p.iframe.name = m, p.iframe.id = m, p.iframe.frameBorder = "0", p.iframe.zIndex = -1, p.iframe.allowTransparency = "true", p.iframe.width = p.canvasNetWidth - p.ifDelta + 0, p.iframe.height = p.canvasNetHeight - p.ifDelta + 0, p.showTitleBar = a.showTitleBar, p.getFrameInnerBorderRadius = a.getFrameInnerBorderRadius, p.frameBorderRadius = a.frameBorderRadius, p.adjustFrameBorderRadius(), p.useIframe = !1, p.canvas.canvasElement.appendChild(p.iframe), p.canvas.canvasElement.appendChild(p.dframe), this.setUseIframe = function (e) {
        p.useIframe = e, p.iframe.style.visibility = "hidden", p.iframe.style.position = "absolute", p.iframe.style.left = "0px", p.iframe.style.top = "0px", p.iframe.style.width = "100%", p.iframe.style.height = "100%", p.dframe.style.visibility = "hidden", p.dframe.style.position = "absolute", p.dframe.style.left = "0px", p.dframe.style.boxSizing = "content-box", p.dframe.style.top = "0px", p.dframe.style.width = "100%", p.dframe.style.height = "100%", p.dframe.style.backgroundColor = "white", p.dframe.style.visibility = e ? (p.iframe.style.visibility = "visible", "hidden") : (p.iframe.style.visibility = "hidden", "visible")
      }, p.setUseIframe(a.useIframe), (p.overrayTransparentScreenEnabled || p.overrayTransparentScreenOnResize) && (p.transparence = document.createElement("span"), p.transparence.style.position = "absolute", p.transparence.style.left = "0px", p.transparence.style.top = "0px", p.transparence.style.width = "0px", p.transparence.style.height = "0px", p.transparence.style.zIndex = 4, p.transparence.style.borderWidth = "0px", p.transparence.style.borderColor = "#ff00ee", p.transparence.style.borderStyle = "none", p.transparence.style.cursor = "default", p.transparence.style.pointerEvents = "none", p.canvas.canvasElement.style.backgroundColor = a.frameBackgroundColor, p.canvas.canvasElement.appendChild(p.transparence))
    }

    function d(e, t, r, o, n, a) {
      d.superConstructor.call(this, e, t, r, o, n, a);
      var i = this;
      document.body.addEventListener("click", function (e) {
        for (var t in i.beanList) {
          i.beanList[t].onBodyClicked(e)
        }
      })
    }

    function T(e, t, r, o, n, a, i) {
      var l = this;
      T.superConstructor.call(this, e, t, r, o, n, a, i), l.htmlElement.typeName = "cmarkerwindow", l.htmlElement.usage = i, l.htmlElement.isRangeLimited = !1, l.htmlElement.style.borderStyle = "none", l.htmlElement.style.zIndex = 1
    }

    function u(e) {
      var t = this,
        r = null,
        o = !0;
      if (e && 0 == e.fixed && (o = !1), e && e.parentElement && (r = e.parentElement), t.hAlign = "left", t.vAlign = "top", e && e.horizontalAlign && (t.hAlign = e.horizontalAlign), e && e.verticalAlign && (t.vAlign = e.verticalAlign), !r && o) {
        var n = document.createElement("div");
        n.id = "jsFrame_fixed_" + t.generateUUID(), n.setAttribute("style", "position:fixed;" + t.hAlign + ":0px;" + t.vAlign + ":0px;margin:0px;padding:0px;"), document.body.appendChild(n), r = n
      } else r = document.body;
      document.onmouseup = function (e) {
        t.windowManager.windowMouseUp(e)
      }, document.onmousemove = function (e) {
        t.windowManager.windowMouseMove(e);
        e.pageX, e.pageY
      }, t.windowManager = new d(r, "windowManager_" + t.generateUUID(), 0, 0, 0, 0), t.domPartsBuilder = null
    }

    function p(e, t, r, o, n, a) {
      var i = this;
      i.id = e, i.x = r, i.y = o, i.frame = null, i._focusTakingCallabck = null, i._focusReleasingCallabck = null, i.frameComponentAlign = n || E.LEFT_TOP, i.htmlElement = t, i.htmlElement.style.zIndex = 50, a && a.childMenu && (i.childMenu = a.childMenu)
    }

    function m() {}

    function h() {
      this.size = 14, this.width = null, this.height = null, this.borderRadius = 2, this.borderWidth = 0, this.borderColorDefault = "#aaaaaa", this.borderColorFocused = this.borderColorDefault, this.borderColorHovered = null, this.borderColorPressed = this.borderColorDefault, this.borderStyleDefault = "solid", this.borderStyleFocused = this.borderStyleDefault, this.borderStyleHovered = null, this.borderStylePressed = this.borderStyleDefault, this.backgroundBoxShadow = null, this.backgroundColorDefault = "transparent", this.backgroundColorFocused = this.backgroundColorDefault, this.backgroundColorHovered = null, this.backgroundColorPressed = this.backgroundColorDefault, this.caption = null, this.captionColorDefault = "white", this.captionColorFocused = this.captionColorDefault, this.captionColorHovered = null, this.captionColorPressed = this.captionColorDefault, this.captionShiftYpx = 0, this.captionFontRatio = 1
    }
    i.prototype.getPartsBuilder = function () {
      return null === this._partsBuilder && (this._partsBuilder = new m), this._partsBuilder
    }, i.prototype.initialize = function () {
      this.onInitialize()
    }, i.prototype.addFrameComponent = function (e, t, r, o, n, a) {
      var i = new p(e, t, r, o, n, a);
      return t._onTakeFocus && t._onReleaseFocus && i.setFocusCallback(t._onTakeFocus, t._onReleaseFocus), this.frameComponents.push(i), i
    }, x.CBEAN = {}, x.CBEAN.HTML_ELEMENT = "span", x.CBEAN.HTML_ELEMENT_ID_PREFIX = "htmlElement_", x.CBEAN.TYPE_NAME = "bean", s.prototype.setMovable = function (e) {
      var t = this;
      return t.htmlElement.argY = e ? t.htmlElement.argX = 1 : t.htmlElement.argX = 0, t.movable = e, t
    }, s.prototype.setParentCanvas = function (e) {
      this.parentCanvas = e, this.htmlElement.parentCanvas = this.parentCanvas
    }, s.prototype.setOnExternalAreaClickedListener = function (e) {
      this.externalAreaClickedListener = e
    }, s.prototype.onBodyClicked = function (e) {
      var t = this,
        r = e.pageX,
        o = e.pageY,
        n = parseInt(t.htmlElement.style.left),
        a = parseInt(t.htmlElement.style.top),
        i = parseInt(t.htmlElement.style.width),
        l = parseInt(t.htmlElement.style.height);
      n < r && r < n + i && a < o && o < a + l || t.externalAreaClickedListener && t.externalAreaClickedListener()
    }, s.prototype.onmouseDown = function (e) {
      var t = this,
        r = t.parent;
      if (0 == e.button) {
        if (r.pullUpDisabled) return !1;
        (t.parentCanvas.currentObject = t).parentCanvas.pullUp(r.id)
      } else if (2 == e.button) return !1;
      return t.parentCanvas.currentObject && (t.parentCanvas.offsetX = e.pageX - parseInt(t.parentCanvas.currentObject.style.left, 10), t.parentCanvas.offsetY = e.pageY - parseInt(t.parentCanvas.currentObject.style.top, 10)), !1
    }, x.CANVAS = {}, x.CANVAS.HTML_ELEMENT = "div", x.CANVAS.WIDTH_ADJUST_20180722 = 2, x.CANVAS.HEIGHT_ADJUST_20180722 = 3, w.prototype.mouseMove = function (e) {
      var t = this;
      if (t.currentObject) {
        t.eventData.targetTypeName = t.currentObject.typeName, t.eventData.targetUsage = t.currentObject.usage, t.eventData.targetObject = t.currentObject;
        var r = e.pageX - t.offsetX,
          o = e.pageY - t.offsetY,
          n = (e.pageX, e.pageY, t.currentObject.style.left),
          a = t.currentObject.style.top,
          i = parseInt(r, 10),
          l = parseInt(o, 10),
          s = i + parseInt(t.currentObject.style.width, 10),
          d = l + parseInt(t.currentObject.style.height, 10),
          u = parseInt(t.canvasElement.style.width, 10),
          p = parseInt(t.canvasElement.style.height, 10),
          m = 0,
          c = 0;
        return 1 == t.currentObject.isRangeLimited && (i <= 0 || u < s || l <= 0 || p < d) ? c = m = 0 : (m = parseInt(r, 10) - parseInt(n, 10), c = parseInt(o, 10) - parseInt(a, 10), t.currentObject.style.left = parseInt(t.currentObject.style.left) + m * t.currentObject.argX + "px", t.currentObject.style.top = parseInt(t.currentObject.style.top) + c * t.currentObject.argY + "px"), t.eventData.deltaX = m, t.eventData.deltaY = c, t.eventData
      }
      return null
    }, w.prototype.mouseUp = function (e) {
      this.currentObject = null, this.mouseDownSource = null
    }, w.prototype.pullUp = function (e) {
      var t = [],
        r = this.beanList;
      for (var o in r) t.push(r[o]);
      var n = r[e];
      this.pullUpSort(n, t, this.baseZIndex), this.onTopObject = n
    }, w.prototype.pullUpSort = function (e, t, r) {
      for (var o in e.htmlElement.style.zIndex = t.length + r, t.sort(function (e, t) {
          return -parseInt(e.htmlElement.style.zIndex, 10) + parseInt(t.htmlElement.style.zIndex, 10)
        }), t) t[o].htmlElement.style.zIndex = t.length - 1 - o + r
    }, w.prototype.removeBean = function (e) {
      var t = this.beanList,
        r = t[e];
      this.canvasElement.removeChild(r.htmlElement), delete t[e]
    }, w.prototype.addBean = function (e) {
      var t = this,
        r = t.beanList,
        o = t.beanIdName,
        n = t.beanNameId;
      (r[e.id] = e).property.name && (n[e.property.name] = e.id, o[e.id] = e.property.name);
      var a = 0;
      for (var i in r) a++;
      e.htmlElement.style.zIndex = a + t.baseZIndex, e.setParentCanvas(t), this.canvasElement.appendChild(e.htmlElement)
    }, x.CFRAME = {}, x.CFRAME.CANVAS_ELEMENT_BGCOLOR = "transparent", x.CFRAME.MODAL_BACKGROUND_FRAME_ID_PREFIX = "window__modal_window_background_", l(F, s), F.prototype.setTitleBarClassName = function (e, t) {
      return e && (this.titleBarClassNameDefault = e, this.titleBarClassNameFocused = e), t && (this.titleBarClassNameFocused = t), this
    }, F.prototype.addFrameComponent = function (e) {
      return this.frameComponentMap[e.id] = e, this.canvas.canvasElement.appendChild(e.htmlElement), this
    }, F.prototype.getFrameComponentElement = function (e) {
      return this.frameComponentMap[e] ? this.frameComponentMap[e].htmlElement : null
    }, F.prototype.removeFrameComponentById = function (e) {
      var t = this.frameComponentMap[e];
      this.canvas.canvasElement.removeChild(t.htmlElement), delete this.frameComponentMap[e]
    }, F.prototype.showFrameComponent = function (e, t) {
      var r = this.getFrameComponentElement(e);
      return r && (r.style.display = t || "flex"), this
    }, F.prototype.hideFrameComponent = function (e) {
      var t = this.getFrameComponentElement(e);
      return t && (t.style.display = "none"), this
    }, F.prototype.hideAllVisibleFrameComponents = function () {
      var e = this.frameComponentMap;
      for (var t in e)
        if (e.hasOwnProperty(t)) {
          var r = e[t].htmlElement;
          "none" === r.style.display && (r._alreadyNone = !0), r.style.display = "none"
        }
    }, F.prototype.showAllVisibleFrameComponents = function () {
      var e = this.frameComponentMap;
      for (var t in e)
        if (e.hasOwnProperty(t)) {
          var r = e[t].htmlElement;
          r._alreadyNone ? r._alreadyNone = null : r.style.display = "flex"
        }
    }, F.prototype.hideFrameComponentChildMenus = function () {
      var e = this.frameComponentMap;
      for (var t in e)
        if (e.hasOwnProperty(t)) {
          var r = e[t];
          r.childMenu && (r.childMenu.style.display = "none")
        }
    }, F.prototype.setTitle = function (e) {
      var t = this;
      if (t.title = e, t.showTitleBar) {
        var r = document.createTextNode(e);
        t.titleBar.firstChild.replaceChild(r, t.titleBar.firstChild.firstChild)
      }
      return t
    }, F.prototype.resize = function (e, t, r, o) {
      var n = this,
        a = parseInt(n.htmlElement.style.left, 10),
        i = parseInt(n.htmlElement.style.top, 10),
        l = parseInt(n.htmlElement.style.width, 10),
        s = parseInt(n.htmlElement.style.height, 10);
      n.htmlElement.style.left = parseInt(a + e, 10) + "px", n.htmlElement.style.top = parseInt(i + t, 10) + "px", n.htmlElement.style.width = parseInt(l + r, 10) + "px", n.htmlElement.style.height = parseInt(s + o, 10) + "px";
      var d = parseInt(n.canvas.canvasElement.style.width, 10),
        u = parseInt(n.canvas.canvasElement.style.height, 10);
      for (var p in n.canvas.canvasElement.style.width = d + r + "px", n.canvas.canvasElement.style.height = u + o + "px", n.showTitleBar && (n.titleBar.style.width = d + r + "px"), n.canvas.beanList) {
        var m = n.canvas.beanList[p];
        "cmarkerwindow" == m.htmlElement.typeName && ("RD" == m.htmlElement.usage ? (m.htmlElement.style.left = parseInt(m.htmlElement.style.left, 10) + r + "px", m.htmlElement.style.top = parseInt(m.htmlElement.style.top, 10) + o + "px") : "DD" == m.htmlElement.usage ? (m.htmlElement.style.width = parseInt(m.htmlElement.style.width, 10) + r + "px", m.htmlElement.style.top = parseInt(m.htmlElement.style.top, 10) + o + "px") : "RR" == m.htmlElement.usage && (m.htmlElement.style.left = parseInt(m.htmlElement.style.left, 10) + r + "px", m.htmlElement.style.height = parseInt(m.htmlElement.style.height, 10) + o + "px"))
      }
    }, F.prototype.canvasMouseDown = function (e) {
      null == this.parentCFrame.parentCanvas.mouseDownSource && (this.parentCFrame.parentCanvas.mouseDownSource = "childcanvas")
    }, F.prototype.mouseUp = function (e) {
      this.canvas.mouseUp(e)
    }, F.prototype.close = function (e) {
      var t = this.parentObject.parentCanvas,
        r = this.parentObject,
        o = r.id;
      r.closeInternally(e, t, o)
    }, F.prototype.closeFrame = function (e) {
      var t = this,
        r = this.parentCanvas;
      t.closeInternally(e, r, t.windowId)
    }, F.prototype.closeInternally = function (e, t, r) {
      var o = this;
      t.removeBean(r), o.modalBackgroundWindowId && (t.removeBean(o.modalBackgroundWindowId), o.modalBackgroundWindowId = null), o.onCloseFrameListener && o.onCloseFrameListener(o)
    }, F.prototype.setOnCloseFrameListener = function (e) {
      this.onCloseFrameListener = e
    }, F.prototype.contextMenu = function () {
      return !1
    }, F.prototype.setTitleBarTextColor = function (e) {
      this.titleBar.style.color = e
    }, F.prototype.setPosition = function (e, t, r) {
      var o = this.getWidth(),
        n = this.getHeight();
      return this._setPositionInternally(e, t, r, o, n), this
    }, F.prototype._setPositionInternally = function (e, t, r, o, n) {
      var a = this;
      r && (a.anchor = r), r && E.LEFT_TOP != r ? E.HCENTER_TOP == r ? (a.htmlElement.style.left = -o / 2 + e + "px", a.htmlElement.style.top = t + "px") : E.RIGHT_TOP == r ? (a.htmlElement.style.left = -o + e + "px", a.htmlElement.style.top = t + "px") : E.LEFT_VCENTER == r ? (a.htmlElement.style.left = e + "px", a.htmlElement.style.top = -n / 2 + t + "px") : E.HCENTER_VCENTER == r ? (a.htmlElement.style.left = -o / 2 + e + "px", a.htmlElement.style.top = -n / 2 + t + "px") : E.RIGHT_VCENTER == r ? (a.htmlElement.style.left = -o + e + "px", a.htmlElement.style.top = -n / 2 + t + "px") : E.LEFT_BOTTOM == r ? (a.htmlElement.style.left = e + "px", a.htmlElement.style.top = -n + t + "px") : E.HCENTER_BOTTOM == r ? (a.htmlElement.style.left = -o / 2 + e + "px", a.htmlElement.style.top = -n + t + "px") : E.RIGHT_BOTTOM == r && (a.htmlElement.style.left = -o + e + "px", a.htmlElement.style.top = -n + t + "px") : (a.htmlElement.style.left = e + "px", a.htmlElement.style.top = t + "px")
    }, F.prototype.getPosition = function () {
      var e, t, r = this,
        o = r.getWidth(),
        n = r.getHeight(),
        a = r.anchor;
      return a && E.LEFT_TOP != a ? E.HCENTER_TOP == a ? (e = parseInt(r.htmlElement.style.left, 10) + o / 2, t = parseInt(r.htmlElement.style.top, 10)) : E.RIGHT_TOP == a ? (e = parseInt(r.htmlElement.style.left, 10) + o, t = parseInt(r.htmlElement.style.top, 10)) : E.LEFT_VCENTER == a ? (e = parseInt(r.htmlElement.style.left, 10), t = parseInt(r.htmlElement.style.top, 10) + n / 2) : E.HCENTER_VCENTER == a ? (e = parseInt(r.htmlElement.style.left, 10) + o / 2, t = parseInt(r.htmlElement.style.top, 10) + n / 2) : E.RIGHT_VCENTER == a ? (e = parseInt(r.htmlElement.style.left, 10) + o, t = parseInt(r.htmlElement.style.top, 10) + n / 2) : E.LEFT_BOTTOM == a ? (e = parseInt(r.htmlElement.style.left, 10), t = parseInt(r.htmlElement.style.top, 10) + n) : E.HCENTER_BOTTOM == a ? (e = parseInt(r.htmlElement.style.left, 10) + o / 2, t = parseInt(r.htmlElement.style.top, 10) + n) : E.RIGHT_BOTTOM == a && (e = parseInt(r.htmlElement.style.left, 10) + o, t = parseInt(r.htmlElement.style.top, 10) + n) : (e = parseInt(r.htmlElement.style.left, 10), t = parseInt(r.htmlElement.style.top, 10)), {
        x: e,
        y: t,
        anchor: a
      }
    }, F.prototype.getLeft = function () {
      return parseInt(this.htmlElement.style.left, 10)
    }, F.prototype.getTop = function () {
      return parseInt(this.htmlElement.style.top, 10)
    }, F.prototype.getWidth = function () {
      return parseInt(this.htmlElement.style.width, 10)
    }, F.prototype.getHeight = function () {
      return parseInt(this.htmlElement.style.height, 10)
    }, F.prototype.getSize = function () {
      return {
        width: this.getWidth(),
        height: this.getHeight()
      }
    }, F.prototype.setSize = function (e, t, r) {
      var o = !0;
      return r && (o = !1), this.resize(0, 0, e - this.getWidth(), t - this.getHeight(), o), this
    }, F.prototype.getWindowId = function () {
      return this.windowId
    }, F.prototype.getName = function () {
      return this.property.name
    }, F.prototype.setName = function (e) {
      this.property.name = e
    }, l(c, F), c.prototype.getFrameView = function () {
      return this.dframe
    }, c.prototype.setHTML = function (e) {
      this.dframe.innerHTML = e
    }, c.prototype.$ = function (e) {
      return this.useIframe ? this.iframe.contentWindow.document.querySelector(e) : this.dframe.querySelector(e)
    }, c.prototype.on = function (r, o, n) {
      var a = this,
        e = a.getFrameComponentElement(r);
      e && (e["on" + o] = function (e) {
        var t = document.getElementById(r + "_child_menu");
        t && "click" === o && ("flex" == t.style.display ? t.style.display = "none" : t.style.display = "flex"), n(a, e, {
          type: "frameComponent",
          id: r,
          eventType: o,
          child: t
        })
      });
      var t = a.$(r);
      t && t.addEventListener(o, function (e) {
        n(a, e, {
          type: "dom",
          id: r,
          eventType: o
        })
      })
    }, c.prototype.adjustFrameBorderRadius = function () {
      var e = this;
      if (0 < parseInt(e.frameBorderRadius, 10)) {
        var t = e.getFrameInnerBorderRadius(e, e._hasFocus);
        e.showTitleBar ? (e.canvas.canvasElement.style.borderBottomRightRadius = t, e.canvas.canvasElement.style.borderBottomLeftRadius = t, e.iframe.style.borderBottomRightRadius = t, e.iframe.style.borderBottomLeftRadius = t, e.titleBar.style.borderTopLeftRadius = t, e.titleBar.style.borderTopRightRadius = t) : (e.canvas.canvasElement.style.borderRadius = t, e.iframe.style.borderRadius = t), e.dframe && (e.dframe.style.borderBottomRightRadius = t, e.dframe.style.borderBottomLeftRadius = t)
      }
    }, c.prototype.handleReleasingFocus = function (e) {
      var t = this;
      for (var r in t._hasFocus = !1, t.titleBar.style.backgroundColor = t.titleBarColorDefault, t.titleBar.style.color = t.titleBarCaptionColorDefault, t.frameBorderColorDefault && (t.htmlElement.style.borderColor = t.frameBorderColorDefault), t.frameBorderWidthDefault && (t.htmlElement.style.borderWidth = t.frameBorderWidthDefault, t.adjustFrameBorderRadius()), "cifwindow" == t.htmlElement.typeName && t.overrayTransparentScreenEnabled && (t.transparence.style.width = parseInt(t.iframe.width, 10) + "px", t.transparence.style.height = parseInt(t.iframe.height, 10) + "px"), t.frameComponentMap) {
        t.frameComponentMap[r].handleReleasingFocus()
      }
      return t.titleBarBorderBottomDefault && (t.titleBar.style.borderBottom = t.titleBarBorderBottomDefault), t.titleBar.className = t.titleBarClassNameDefault, t
    }, c.prototype.handleTakingFocus = function (e) {
      var t = this;
      for (var r in t._hasFocus = !0, t._hasFocus = Date.now(), t.overrayTransparentScreenEnabled && (t.transparence.style.width = "0px", t.transparence.style.height = "0px"), t.titleBar.style.backgroundColor = t.titleBarColorFocused, t.titleBar.style.color = t.titleBarCaptionColorFocused, t.frameBorderColorFocused && (t.htmlElement.style.borderColor = t.frameBorderColorFocused), t.frameBorderWidthFocused && (t.htmlElement.style.borderWidth = t.frameBorderWidthFocused, t.adjustFrameBorderRadius()), t.titleBarBorderBottomFocused && (t.titleBar.style.borderBottom = t.titleBarBorderBottomFocused), t.frameComponentMap) {
        t.frameComponentMap[r].handleTakingFocus()
      }
      return t.titleBar.className = t.titleBarClassNameFocused, t
    }, F.prototype.show = function (e) {
      return this.htmlElement.style.display = "flex", e && 0 == e.requestFocus || this.requestFocus(), this
    }, F.prototype.showModal = function (e) {
      var t = this,
        r = new i;
      r.showTitleBar = !0, r.showCloseButton = !1, r.frameBorderRadius = "0px", r.frameBorderStyle = "none", r.frameBorderWidthDefault = "0px", r.frameBorderWidthFocused = "0px", r.frameBoxShadow = null, r.frameBackgroundColor = "transparent", r.frameComponents = [], r.frameHeightAdjust = 0, r.titleBarHeight = "0px", r.titleBarBorderBottomFocused = null, r.titleBarCaptionLeftMargin = "0px", r.onInitialize = function () {}, r.pullUpDisabled = !0;
      var o = t.parentCanvas,
        n = x.CFRAME.MODAL_BACKGROUND_FRAME_ID_PREFIX + t.id,
        a = new c(n, 0, 0, 1, 1, r);
      a.setSize(window.innerWidth, window.innerHeight, !0), a.setResizable(!1), window.addEventListener("resize", function () {
        a.setSize(window.innerWidth, window.innerHeight, !0)
      }), t.modalBackgroundWindowId = n, a.hide(), o.addWindow(a), a.setTitle("").getFrameView().innerHTML = '<div class="jsframe-modal-window-background"></div>', a.getFrameView().style.backgroundColor = "rgba(0,0,0,0.0)", a.show(), t.show(), e && t.setOnCloseFrameListener(e)
    }, c.prototype.hide = function () {
      return this.htmlElement.style.display = "none", this
    }, c.prototype.onmouseDown = function (e) {
      document.body.ondrag = function () {
        return !1
      }, this.decorator = F.prototype.onmouseDown, this.decorator(e);
      var t = this.parent,
        r = this.parentCanvas;
      for (var o in r.beanList) {
        var n = r.beanList[o];
        o == t.getWindowId() || n.handleReleasingFocus()
      }
      t.handleTakingFocus()
    }, c.prototype.mouseUp = function (e) {
      var t = this;
      (t.overrayTransparentScreenEnabled || t.overrayTransparentScreenOnResize) && (t.parentCanvas.onTopObject == t ? (t.transparence.style.width = "0px", t.transparence.style.height = "0px") : t.overrayTransparentScreenEnabled && (t.transparence.style.width = parseInt(t.iframe.width) + "px", t.transparence.style.height = parseInt(t.iframe.height) + "px")), t.decorator = F.prototype.mouseUp, t.decorator(e), document.body.ondrag = null, document.body.onselectstart = null, t.transparence.style.pointerEvents = "none"
    }, c.prototype.setMinFrameSize = function (e, t) {
      this.minFrameWidth = e, this.minWindowHeight = t
    }, c.prototype.resize = function (e, t, r, o, n) {
      var a = this;
      if (!a.resizable && n) return null;
      var i = parseInt(a.htmlElement.style.left, 10),
        l = parseInt(a.htmlElement.style.top, 10),
        s = parseInt(a.htmlElement.style.width, 10),
        d = parseInt(a.htmlElement.style.height, 10);
      n && s + r < a.minFrameWidth & r < 0 && (a.htmlElement.style.width = s + "px", r = 0), n && d + o < a.minWindowHeight & o < 0 && (a.htmlElement.style.height = d + "px", o = 0), a.htmlElement.style.left = i + e + "px", a.htmlElement.style.top = l + t + "px", a.htmlElement.style.width = s + r + "px", a.htmlElement.style.height = d + o + "px";
      var u = parseInt(a.canvas.canvasElement.style.width, 10),
        p = parseInt(a.canvas.canvasElement.style.height, 10);
      for (var m in a.canvas.canvasElement.style.width = u + r + "px", a.canvas.canvasElement.style.height = p + o + "px", a.titleBar.style.width = u - a.ifDelta + r + 0 + "px", a.iframe.width = u - a.ifDelta + r + 0 + "px", a.iframe.height = p - a.ifDelta + o + a.frameHeightAdjust + "px", (a.overrayTransparentScreenEnabled || a.overrayTransparentScreenOnResize) && (a.transparence.style.width = parseInt(a.iframe.width) + "px", a.transparence.style.height = parseInt(a.iframe.height) + "px"), a.frameComponentMap) {
        a.frameComponentMap[m].updateAlign()
      }
      for (var c in a.canvas.beanList) {
        var h = a.canvas.beanList[c];
        "cmarkerwindow" == h.htmlElement.typeName && ("RD" == h.htmlElement.usage ? (h.htmlElement.style.left = parseInt(h.htmlElement.style.left) + r + "px", h.htmlElement.style.top = parseInt(h.htmlElement.style.top) + o + "px") : "DD" == h.htmlElement.usage ? (h.htmlElement.style.width = parseInt(h.htmlElement.style.width) + r + "px", h.htmlElement.style.top = parseInt(h.htmlElement.style.top) + o + "px") : "RR" == h.htmlElement.usage && (h.htmlElement.style.left = parseInt(h.htmlElement.style.left) + r + "px", h.htmlElement.style.height = parseInt(h.htmlElement.style.height) + o + "px"))
      }
    }, c.prototype.resizeDirect = function (e, t, r) {
      var o = this;
      if (!o.resizable && r) return null;
      o.htmlElement.style.width = e + "px", o.htmlElement.style.height = t + "px";
      parseInt(o.canvas.canvasElement.style.width), parseInt(o.canvas.canvasElement.style.height);
      for (var n in o.canvas.canvasElement.style.width = e + "px", o.canvas.canvasElement.style.height = t - o.titleBar.style.height + "px", o.titleBar.style.width = e - o.ifDelta + "px", o.iframe.width = e - o.ifDelta + "px", o.iframe.height = t - o.ifDelta + o.frameHeightAdjust + "px", (o.overrayTransparentScreenEnabled || o.overrayTransparentScreenOnResize) && (o.transparence.style.width = parseInt(o.iframe.width) + "px", o.transparence.style.height = parseInt(o.iframe.height) + "px"), o.frameComponentMap) {
        o.frameComponentMap[n].updateAlign()
      }
      for (var a in o.canvas.beanList) {
        var i = o.canvas.beanList[a];
        "cmarkerwindow" == i.htmlElement.typeName && ("RD" == i.htmlElement.usage ? (i.htmlElement.style.left = e + "px", i.htmlElement.style.top = t + "px") : "DD" == i.htmlElement.usage ? (i.htmlElement.style.width = e + "px", i.htmlElement.style.top = t + "px") : "RR" == i.htmlElement.usage && (i.htmlElement.style.left = e + "px", i.htmlElement.style.height = t + "px"))
      }
    }, c.prototype.requestFocus = function () {
      var e = this.parentCanvas.beanList;
      for (var t in e) {
        var r = e[t];
        t == this.getWindowId() ? r.handleTakingFocus() : r.handleReleasingFocus()
      }
      this.parentCanvas.pullUp(this.id)
    }, c.prototype.setUrl = function (r) {
      var n = this;
      return new Promise(function (e, t) {
        r ? n.setUseIframe(!0) : (n.setUseIframe(!1), e()), n.iframe.src = r, n.iframe.onload = function () {
          n.iframe.contentWindow.document.onmousemove = function (e) {
            var t = n.getLeft(),
              r = n.getTop(),
              o = document.createEvent("MouseEvents");
            o.initMouseEvent("mousemove", !0, !1, window, e.detail, e.screenX, e.screenY, e.pageX + t, e.pageY + r, e.ctrlKey, e.altKey, e.shiftKey, e.metaKey, e.button, null), n.parentCanvas.windowMouseMove(o), n.onMouseMoveOnIframe && n.onMouseMoveOnIframe(o)
          }, n.iframe.contentWindow.document.onmouseup = function (e) {
            var t = n.getLeft(),
              r = n.getTop(),
              o = document.createEvent("MouseEvents");
            o.initMouseEvent("mouseup", !0, !1, window, e.detail, e.screenX, e.screenY, e.pageX + t, e.pageY + r, e.ctrlKey, e.altKey, e.shiftKey, e.metaKey, e.button, null), n.parentCanvas.windowMouseUp(o), n.onMouseUpOnIframe && n.onMouseUpOnIframe(o)
          }, e(n, n.iframe.contentWindow.document)
        }
      })
    }, c.prototype.getIfDocument = function () {
      return this.iframe.contentWindow.document
    }, c.prototype.setScrolling = function (e) {
      this.iframe.scrolling = e
    }, c.prototype.getScrolling = function (e) {
      return this.iframe.scrolling
    }, c.prototype.setResizable = function (e) {
      return this.resizable = e, this.enableMarkers(e), this
    }, c.prototype.setControl = function (e) {
      e && ((e.frame = this).control = this.jsFrame.createWindowEventHelper(e), this.control.setupDefaultEvents(e))
    }, l(d, w), d.prototype.getWindow = function (e) {
      return this.beanList[e]
    }, d.prototype.addWindow = function (e) {
      var t = e.getWindowId(),
        r = e.getName();
      this.beanIdName[t] = r, this.beanNameId[r] = t, this.addBean(e)
    }, d.prototype.containsWindowName = function (e) {
      if (this.beanNameId[e]) return !0
    }, d.prototype.getWindowByName = function (e) {
      var t = this.beanNameId[e];
      return t ? this.getWindow(t) : null
    }, d.prototype.windowMouseMove = function (e) {
      if (null == this.currentObject) return null;
      var t = !1,
        r = this.beanList;
      for (var o in r) {
        var n = r[o],
          a = n.canvas.mouseMove(e);
        if (t |= null != a, null != a && "cmarkerwindow" == a.targetTypeName) {
          var i = a.targetObject;
          n.transparence.style.pointerEvents = "auto", "RD" == i.usage ? n.resize(0, 0, a.deltaX, a.deltaY, !0) : "DD" == i.usage ? n.resize(0, 0, 0, a.deltaY, !0) : "RR" == i.usage && n.resize(0, 0, a.deltaX, 0, !0)
        }
      }
      t || "childcanvas" == this.mouseDownSource || this.mouseMove(e)
    }, d.prototype.windowMouseUp = function (e) {
      this.mouseUp(e);
      var t = this.beanList;
      for (var r in t) {
        t[r].mouseUp(e)
      }
    }, d.prototype.removeBean = function (e) {
      var t = this,
        r = t.beanList,
        o = r[e];
      if (null != o) {
        var n = o._hasFocus;
        t.canvasElement.removeChild(o.htmlElement), delete r[e];
        var a = t.beanIdName[e];
        a && (delete t.beanIdName[e], delete t.beanNameId[a]);
        var i = 0,
          l = null;
        if (n) {
          for (var e in r) {
            var s = r[e];
            i <= s._hasFocusTime && !s.pullUpDisabled && (i = s._hasFocusTime, l = s)
          }
          l && l.requestFocus()
        }
        o.parentCanvas = null
      }
    }, l(T, s), u.prototype.getDomPartsBuilder = function () {
      return this.domPartsBuilder || (this.domPartsBuilder = new m), this.domPartsBuilder
    }, u.prototype.create = function (e) {
      var t = {};
      t.name = e.name;
      var r = e.title,
        o = e.left,
        n = e.top,
        a = e.width,
        i = e.height,
        l = e.appearance,
        s = e.appearanceName,
        d = e.appearanceParam,
        u = e.style,
        p = e.minWidth,
        m = e.minHeight,
        c = e.html,
        h = e.resizable,
        f = e.movable,
        y = e.url,
        b = e.urlLoaded;
      s && (l = this.createPresetStyle(s, {
        appearanceParam: d
      }));
      var g = this.createFrame(o, n, a, i, l, t);
      if (r && g.setTitle(r), c && g.setHTML(c), y) {
        var C = g.setUrl(y);
        b && C.then(b)
      }
      if (0 == h && g.setResizable(!1), 0 == f && g.setMovable(!1), p && m && (g.minFrameWidth = p), m && (g.minWindowHeight = m), u) {
        var v = g.getFrameView();
        for (var B in u) u.hasOwnProperty(B) && (v.style[B] = u[B])
      }
      return g
    }, u.prototype.createFrame = function (e, t, r, o, n, a) {
      n || (n = this.createFrameAppearance()), n.initialize(), e || (e = 0), t || (t = 0), r || (r = 128), o || (o = 128);
      var i = new c("window_" + this.generateUUID(), e, t, r, o, n);
      if (i.jsFrame = this, a && a.name && i.setName(a.name), i.hide(), this.windowManager.addWindow(i), n.getTitleBarStyle) {
        var l = n.getTitleBarStyle();
        l && i.setTitleBarClassName(l.titleBarClassNameDefault, l.titleBarClassNameFocused)
      }
      return i
    }, u.prototype.containsWindowName = function (e) {
      return this.windowManager.containsWindowName(e)
    }, u.prototype.getWindowByName = function (e) {
      return this.windowManager.getWindowByName(e)
    }, u.prototype.generateUUID = function () {
      var r = Date.now();
      return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (e) {
        var t = (r + 16 * Math.random()) % 16 | 0;
        return r = Math.floor(r / 16), ("x" == e ? t : 3 & t | 8).toString(16)
      })
    }, u.prototype.createFrameAppearance = function () {
      return new i
    }, u.prototype.createAnimator = function () {
      return new n
    }, u.prototype.createWindowEventHelper = function (e) {
      return e || (e = {}), e.verticalAlign = this.vAlign, e.horizontalAlign = this.hAlign, new o(e)
    }, u.prototype.createPresetStyle = function (e, t) {
      var r = this.createFrameAppearance();
      if (t && t.focusedFrameOnly && (r.focusedFrameOnly = t.focusedFrameOnly), a[e]) {
        var o = a[e],
          n = null;
        return t && t.appearanceParam && (n = t.appearanceParam), o.getStyle(r, n)
      }
      return r
    }, u.prototype.showToast = function (e) {
      if (e) {
        var t = this,
          r = 60,
          o = 260,
          n = 1e3,
          a = window.innerHeight - 10 - r / 2,
          i = window.innerHeight - 20 - r / 2,
          l = "",
          s = !1,
          d = {
            borderRadius: "10px",
            background: "rgba(0,0,0,0.8)"
          };
        e.style && (d = e.style), e.height && (r = e.height), e.width && (o = e.width), e.duration && (n = e.duration), e.align && ("top" == e.align ? (a = 10 + r / 2, i = 20 + r / 2) : "center" == e.align && (a = window.innerHeight / 2, i = window.innerHeight / 2)), e.html && (l = e.html), e.text && (l = e.text), s = 1 == e.closeButton;
        var u = t.createPresetStyle("toast");
        d.borderRadius && (u.frameBorderRadius = d.borderRadius), e.closeButtonColor && (u.captionClor = e.closeButtonColor);
        var p = t.create({
          name: "toast_" + t.generateUUID(),
          width: o,
          height: r,
          movable: !1,
          resizable: !1,
          appearance: u,
          style: d,
          html: '<div id="my_element" style="box-sizing:border-box;display: flex;justify-content: center;align-items: center;padding:10px;font-size:16px;color:darkgray;height:' + r + 'px">' + l + "</div>"
        });
        s || p.hideFrameComponent("closeButton");
        var m = window.innerWidth / 2;
        "right" == t.hAlign && (m = -m), "bottom" == t.vAlign && (a -= window.innerHeight, i -= window.innerHeight), t.createAnimator().set(p).setDuration(300).fromPosition(m, a, "CENTER_CENTER").toPosition(m, i, "CENTER_CENTER").toAlpha(1).fromAlpha(0).start(0, !1).then(function (e) {
          return e.setDuration(300).fromPosition(m, i, "CENTER_CENTER").toPosition(m, a, "CENTER_CENTER").fromAlpha(1).toAlpha(.5).start(n, !1)
        }).then(function (e) {
          e.get().closeFrame()
        })
      }
    }, p.prototype.setFocusCallback = function (e, t) {
      this._focusTakingCallabck = e, this._focusReleasingCallabck = t
    }, p.prototype.setFrame = function (e) {
      this.frame = e, this.htmlElement.parentObject = e, this.updateAlign()
    }, p.prototype.updateAlign = function () {
      var e = this,
        t = e.frameComponentAlign,
        r = e.frame,
        o = e.htmlElement.style;
      o.userSelect = "none";
      var n = e.x,
        a = e.y,
        i = r.getWidth(),
        l = r.getHeight(),
        s = o.width,
        d = o.height;
      E.LEFT_TOP == t ? (o.left = n + "px", o.top = a + "px") : E.HCENTER_TOP == t ? (o.left = parseInt(i) / 2 - parseInt(s) / 2 + n + "px", o.top = a + "px") : E.RIGHT_TOP == t ? (o.left = parseInt(i) - parseInt(s) + n + "px", o.top = a + "px") : E.LEFT_VCENTER == t ? (o.left = n + "px", o.top = parseInt(l) / 2 - parseInt(d) / 2 + a + "px") : E.HCENTER_VCENTER == t ? (o.left = parseInt(i) / 2 - parseInt(s) / 2 + n + "px", o.top = parseInt(l) / 2 - parseInt(d) / 2 + a + "px") : E.RIGHT_VCENTER == t ? (o.left = parseInt(i) - parseInt(s) + n + "px", o.top = parseInt(l) / 2 - parseInt(d) / 2 + a + "px") : E.LEFT_BOTTOM == t ? (o.left = n + "px", o.top = parseInt(l) - parseInt(d) + a + "px") : E.HCENTER_BOTTOM == t ? (o.left = parseInt(i) / 2 - parseInt(s) / 2 + n + "px", o.top = parseInt(l) - parseInt(d) + a + "px") : E.RIGHT_BOTTOM == t && (o.left = parseInt(i) - parseInt(s) + n + "px", o.top = parseInt(l) - parseInt(d) + a + "px")
    }, p.prototype.handleTakingFocus = function () {
      this._focusTakingCallabck && this._focusTakingCallabck()
    }, p.prototype.handleReleasingFocus = function () {
      this._focusReleasingCallabck && this._focusReleasingCallabck()
    }, m.prototype.buildTextButtonAppearance = function () {
      return new h
    }, m.prototype.buildButton = function (e) {
      return this.buildTextButton(e)
    }, m.prototype.buildTextButton = function (e) {
      var t = e.size,
        r = t,
        o = t;
      null != e.width && (r = e.width), null != e.height && (o = e.height);
      var n = document.createElement("div"),
        a = e.borderWidth,
        i = e.borderRadius,
        l = e.borderColorDefault,
        s = e.borderColorFocused,
        d = e.borderColorHovered,
        u = e.borderColorPressed,
        p = e.borderStyleDefault,
        m = e.borderStyleFocused,
        c = e.borderStyleHovered,
        h = e.borderStylePressed,
        f = e.backgroundColorDefault,
        y = e.backgroundColorFocused,
        b = e.backgroundColorHovered,
        g = e.backgroundColorPressed,
        C = e.backgroundBoxShadow,
        v = e.fa,
        B = e.caption,
        E = e.imageDefault,
        x = e.imageFocused,
        w = e.imageHovered,
        F = e.imagePressed;
      E && (E.style.pointerEvents = "none"), x && (x.style.pointerEvents = "none"), w && (w.style.pointerEvents = "none"), F && (F.style.pointerEvents = "none");
      var T = e.captionColorDefault,
        A = e.captionColorFocused,
        D = e.captionColorHovered,
        I = e.captionColorPressed,
        S = e.captionShiftYpx,
        k = e.captionFontRatio;
      n._hasFrameFocus = !1, n._isMouseDown = !1, n.style.position = "absolute", n.style.top = "0px", n.style.left = "0px", n.style.width = r + "px", n.style.height = o + "px", n.style.cursor = "pointer", n.style.margin = "0px", n.style.padding = "0px", n.style.boxSizing = "content-box", n.style.fontFamily = "sans-serif", n.onmousedown = function (e) {
        n._isMouseDown = !0, n._handleFocusDrawing("onmousedown")
      }, n.onmouseout = function (e) {
        n._isMouseDown = !1, n._handleFocusDrawing("onmouseout")
      }, n.onmouseover = function (e) {
        n._handleHoverDrawing()
      }, n.onmouseup = function (e) {
        n._isMouseDown = !1, n._handleFocusDrawing("onmouseup")
      }, n._onTakeFocus = function () {
        n._hasFrameFocus = !0, n._handleFocusDrawing("_onTakeFocus")
      }, n._onReleaseFocus = function () {
        n._hasFrameFocus = !1, n._handleFocusDrawing("_onReleaseFocus")
      }, n._handleFocusDrawing = function (e) {
        n._hasFrameFocus ? n._isMouseDown ? (n.style.borderColor = u, n.style.borderStyle = h, n.style.backgroundColor = g, n.style.color = I, F && (n.innerHTML = "", n.appendChild(F))) : (n.style.borderColor = s, n.style.borderStyle = m, n.style.backgroundColor = y, n.style.color = A, x && (n.innerHTML = "", n.appendChild(x))) : n._isMouseDown ? (n.style.borderColor = u, n.style.borderStyle = h, n.style.backgroundColor = g, n.style.color = I, F && (n.innerHTML = "", n.appendChild(F))) : (n.style.borderColor = l, n.style.borderStyle = p, n.style.backgroundColor = f, n.style.color = T, E && (n.innerHTML = "", n.appendChild(E)))
      }, n._handleHoverDrawing = function () {
        n._hasFrameFocus, d && (n.style.borderColor = d), c && (n.style.borderStyle = c), b && (n.style.backgroundColor = b), D && (n.style.color = D), w && (n.innerHTML = "", n.appendChild(w))
      }, n.style.textAlign = "center", n.style.fontSize = o * k + "px", n.style.lineHeight = o + "px", n.style.borderWidth = "1px", null != a && (n.style.borderWidth = a + "px"), null != C && (n.style.boxShadow = C), n.style.borderRadius = i + parseInt(n.style.borderWidth) + "px";
      if (E) n.innerHTML = "", n.appendChild(E);
      else if (B) {
        (_ = document.createElement("span")).style.width = "100%", _.style.marginTop = S + "px", _.style.display = "inline-block", _.style.textAlign = "center", _.style.fontFamily = "sans-serif", _.appendChild(document.createTextNode(B)), n.appendChild(_)
      } else if (v) {
        var _;
        (_ = document.createElement("span")).style.width = "100%", _.style.marginTop = S + "px", _.style.display = "inline-block", _.style.textAlign = "center", _.style.fontFamily = "sans-serif", _.innerHTML = '<i class="' + v + '"></i>', n.appendChild(_)
      } else 0;
      return n._handleFocusDrawing(), n
    }, Object.freeze(x)
  }, function (e, t, r) {
    var o = r(6);
    "string" == typeof o && (o = [
      [e.i, o, ""]
    ]);
    var n = {
      hmr: !0,
      transform: void 0,
      insertInto: void 0
    };
    r(1)(o, n);
    o.locals && (e.exports = o.locals)
  }, function (e, t, r) {
    (e.exports = r(0)(!1)).push([e.i, ".jsframe-titlebar-default {\r\n    background: -webkit-gradient(linear, left top, left bottom, color-stop(0.0, #f5f5f5, color-stop(1.0, #f8f7f2)));\r\n    background: -webkit-linear-gradient(top, #f5f5f5, #f8f7f2);\r\n    background: -moz-linear-gradient(top, #f5f5f5, #f8f7f2);\r\n    background: linear-gradient(top, #f5f5f5, #f8f7f2);\r\n    border-top-left-radius: 6px;\r\n    border-top-right-radius: 6px;\r\n}\r\n\r\n.jsframe-titlebar-focused {\r\n    /* (c)2015 Johannes Jakob\r\n       Made with <3 in Germany */\r\n    background: -webkit-gradient(linear, left top, left bottom, color-stop(0.0, #ebebeb, color-stop(1.0, #d5d5d5)));\r\n    background: -webkit-linear-gradient(top, #ebebeb, #d5d5d5);\r\n    background: -moz-linear-gradient(top, #ebebeb, #d5d5d5);\r\n    background: linear-gradient(top, #ebebeb, #d5d5d5);\r\n    border-top-left-radius: 6px;\r\n    border-top-right-radius: 6px;\r\n}\r\n\r\n.jsframe-modal-window-background {\r\n    background: rgba(0, 0, 0, 0.6);\r\n    height: 100%;\r\n    widdth: 100%\r\n}", ""])
  }, function (e, t) {
    e.exports = function (e) {
      var t = "undefined" != typeof window && window.location;
      if (!t) throw new Error("fixUrls requires window.location");
      if (!e || "string" != typeof e) return e;
      var n = t.protocol + "//" + t.host,
        a = n + t.pathname.replace(/\/[^\/]*$/, "/");
      return e.replace(/url\s*\(((?:[^)(]|\((?:[^)(]+|\([^)(]*\))*\))*)\)/gi, function (e, t) {
        var r, o = t.trim().replace(/^"(.*)"$/, function (e, t) {
          return t
        }).replace(/^'(.*)'$/, function (e, t) {
          return t
        });
        return /^(#|data:|http:\/\/|https:\/\/|file:\/\/\/|\s*$)/i.test(o) ? e : (r = 0 === o.indexOf("//") ? o : 0 === o.indexOf("/") ? n + o : a + o.replace(/^\.\//, ""), "url(" + JSON.stringify(r) + ")")
      })
    }
  }, function (e, t, r) {
    "use strict";
    var n = r(2),
      p = r(3);

    function o(e) {
      this.windowMode = "default", this.styleDisplay = "flex", this.horizontalAlign = "left", this.verticalAlign = "top", this.keyListener = null, this.minimizeButton = null, this.maximizeButton = null, this.demaximizeButton = null, this.deminimizeButton = null, e.styleDisplay && (this.styleDisplay = e.styleDisplay), e.minimizeButton && (this.minimizeButton = e.minimizeButton), e.deminimizeButton && (this.deminimizeButton = e.deminimizeButton), e.maximizeButton && (this.maximizeButton = e.maximizeButton), e.demaximizeButton && (this.demaximizeButton = e.demaximizeButton), e.hideButton && (this.hideButton = e.hideButton), e.horizontalAlign && (this.horizontalAlign = e.horizontalAlign), e.verticalAlign && (this.verticalAlign = e.verticalAlign), this.animationEnabled = !1, this.animationDuration = 100, this.frame = e.frame, this.hideFrameBorder = !0, this.hideTitleBar = !0, this.restoreKey = null, this.restoreDuration = null, this.restoreCallback = null, this.statsStore = {}, e.animation && (this.animationEnabled = e.animation), e.animationDuration && (this.animationDuration = e.animationDuration), this.resizeListener = this.handleOnResize.bind(this), this.eventListeners = {}
    }
    o.prototype.on = function (e, t) {
      this.eventListeners[e] = t
    }, o.prototype.doMaximize = function (e) {
      var t = this,
        r = t.frame;
      window.addEventListener("resize", t.resizeListener), t.saveCurrentWindowStats("maximize_mode"), t.hideTitleBar = !!e && e.hideTitleBar, t.hideTitleBar ? r.hideAllVisibleFrameComponents() : (t.maximizeButton && r.hideFrameComponent(t.maximizeButton), t.demaximizeButton && r.showFrameComponent(t.demaximizeButton, t.styleDisplay)), r.setMovable(!1), r.setResizable(!1), e && e.restoreKey && (t.restoreKey = e.restoreKey), e && e.restoreDuration && (t.restoreDuration = e.restoreDuration), e && e.restoreCallback && (t.restoreCallback = e.restoreCallback), t.renderMaximizedMode({
        animation: t.animationEnabled,
        callback: e && e.callback ? e.callback : null,
        duration: e && e.duration ? e.duration : null
      })
    }, o.prototype.renderMaximizedMode = function (e) {
      var t = this,
        r = t.frame,
        o = t.loadWindowStats("maximize_mode"),
        n = 0,
        a = 0,
        i = 0,
        l = 0;
      t.hideTitleBar && (n = 0, a = -o.titleBarHeight), l = t.hideFrameBorder ? (i = window.innerWidth, window.innerHeight + (t.hideTitleBar ? o.titleBarHeight : 0)) : (i = window.innerWidth - 2 * o.frameBorderWidthDefault, window.innerHeight - 2 * o.frameBorderWidthDefault + (t.hideTitleBar ? o.titleBarHeight : 0)), "right" == t.horizontalAlign && (n = -i), "bottom" == t.verticalAlign && (a = -l);
      var s = function () {
        r.setPosition(n, a), t.hideFrameBorder && (r.frameBorderWidthDefault = 0, r.frameBorderWidthFocused = 0, r.htmlElement.style.borderWidth = "0px"), r.setSize(i, l, !0), t.hideTitleBar && (t.restoreKey && (t.keyListener = function (e) {
          e.key === t.restoreKey && t.doDemaximize({
            duration: t.restoreDuration ? t.restoreDuration : null,
            callback: t.restoreCallback ? t.restoreCallback : null
          })
        }), window.addEventListener("keydown", t.keyListener), r.iframe && r.iframe.contentWindow.addEventListener("keydown", t.keyListener)), t.windowMode = "maximized", e && e.callback && e.callback(t.frame, {
          eventType: "maximized"
        }), t.eventListeners.maximized && t.eventListeners.maximized(t.frame, {
          eventType: "maximized"
        })
      };
      e && e.animation ? t.animateFrame({
        frame: r,
        from: o,
        to: {
          left: n,
          top: a,
          width: i,
          height: l
        },
        duration: e.duration ? e.duration : t.animationDuration,
        callback: s
      }) : s()
    }, o.prototype.doDemaximize = function (e) {
      var t = this,
        r = t.frame;
      t.hasWindowStats("maximize_mode") && (t.hideTitleBar || (t.maximizeButton && r.showFrameComponent(t.maximizeButton, t.styleDisplay), t.demaximizeButton && r.hideFrameComponent(t.demaximizeButton)), t.restoreWindow({
        restorePosition: !0,
        restoreMode: "maximize_mode",
        animation: t.animationEnabled,
        callback: e && e.callback ? e.callback : null,
        forceShowFrameComponents: t.animationEnabled && t.hideTitleBar,
        duration: e && e.duration ? e.duration : null,
        eventType: "demaximized"
      }))
    }, o.prototype.handleOnResize = function () {
      this.renderMaximizedMode()
    }, o.prototype.doMinimize = function (e) {
      var t = this.frame;
      this.saveCurrentWindowStats("minimize_mode"), t.setResizable(!1), this.renderMinimizedMode({
        animation: this.animationEnabled,
        callback: e && e.callback ? e.callback : null,
        duration: e && e.duration ? e.duration : null
      })
    }, o.prototype.renderMinimizedMode = function (e) {
      var t = this,
        r = t.frame,
        o = t.loadWindowStats("minimize_mode"),
        n = t.getCurrentWindowStats(),
        a = t.getCurrentWindowStats();
      a.height = o.titleBarHeight;
      var i = function () {
        r.setSize(a.width, a.height, !0), t.windowMode = "minimized", t.minimizeButton && r.hideFrameComponent(t.minimizeButton), t.deminimizeButton && r.showFrameComponent(t.deminimizeButton, t.styleDisplay), e.callback && e.callback(t.frame, {
          eventType: "minimized"
        }), t.eventListeners.minimized && t.eventListeners.minimized(t.frame, {
          eventType: "minimized"
        })
      };
      e && e.animation ? t.animateFrame({
        toAlpha: 1,
        duration: e.duration ? e.duration : t.animationDuration,
        frame: r,
        from: n,
        to: a,
        callback: i
      }) : i()
    }, o.prototype.doDeminimize = function (e) {
      var t = this,
        r = t.frame;
      t.hasWindowStats("minimize_mode") && (t.minimizeButton && r.showFrameComponent(t.minimizeButton, t.styleDisplay), t.deminimizeButton && r.hideFrameComponent(t.deminimizeButton), t.restoreWindow({
        restorePosition: !1,
        restoreMode: "minimize_mode",
        animation: t.animationEnabled,
        duration: e && e.duration ? e.duration : null,
        callback: e && e.callback ? e.callback : null,
        eventType: "deminimized"
      }))
    }, o.prototype.doHide = function (e) {
      var t = this.frame;
      this.saveCurrentWindowStats("hide_mode"), t.setResizable(!1), this.renderHideMode({
        silent: e.silent,
        animation: this.animationEnabled,
        duration: e && e.duration ? e.duration : null,
        callback: e && e.callback ? e.callback : null,
        align: e && e.align ? e.align : null,
        offset: e.offset
      })
    }, o.prototype.renderHideMode = function (e) {
      var t = this,
        r = t.frame,
        o = t.loadWindowStats("hide_mode"),
        n = t.getCurrentWindowStats(),
        a = {
          x: 0,
          y: 0
        };
      e.offset && (a = e.offset);
      var i = 0,
        l = 0,
        s = e && e.align ? e.align : "LEFT_TOP";
      s && p.LEFT_TOP != s ? p.HCENTER_TOP == s ? (i = n.left + n.width / 2, l = n.top) : p.RIGHT_TOP == s ? (i = n.left + n.width, l = n.top) : p.LEFT_VCENTER == s ? (i = n.left, l = n.top + n.height / 2) : p.HCENTER_VCENTER == s ? (i = n.left + n.width / 2, l = n.top + n.height / 2) : p.RIGHT_VCENTER == s ? (i = n.left + n.width, l = n.top + n.height / 2) : p.LEFT_BOTTOM == s ? (i = n.left, l = n.top + n.height) : p.HCENTER_BOTTOM == s ? (i = n.left + n.width / 2, l = n.top + n.height) : p.RIGHT_BOTTOM == s ? (i = n.left + n.width, l = n.top + n.height) : "ABSOLUTE" == s && (i = a.x, l = a.y) : (i = n.left, l = n.top);
      var d = {
          left: i,
          top: l,
          width: 0,
          height: o.titleBarHeight
        },
        u = function () {
          r.setSize(d.width, d.height, !0), t.windowMode = "hid", e && e.callback && e.callback(t.frame, {
            eventType: "hid"
          }), t.eventListeners.hid && t.eventListeners.hid(t.frame, {
            eventType: "hid"
          })
        };
      r.hideAllVisibleFrameComponents(), e && e.animation ? t.animateFrame({
        fromAlpha: e.silent ? 0 : 1,
        toAlpha: 0,
        duration: e.duration ? e.duration : t.animationDuration,
        frame: r,
        from: n,
        to: d,
        callback: u
      }) : u()
    }, o.prototype.doDehide = function (e) {
      this.frame;
      this.hasWindowStats("hide_mode") && this.restoreWindow({
        duration: e && e.duration ? e.duration : null,
        restorePosition: !0,
        restoreMode: "hide_mode",
        animation: this.animationEnabled,
        forceShowFrameComponents: !0,
        callback: e && e.callback ? e.callback : null,
        eventType: "dehided"
      })
    }, o.prototype.loadWindowStats = function (e) {
      return this.statsStore[e]
    }, o.prototype.saveCurrentWindowStats = function (e) {
      this.statsStore[e] = this.getCurrentWindowStats()
    }, o.prototype.clearWindowStats = function (e) {
      this.statsStore[e] = null
    }, o.prototype.hasWindowStats = function (e) {
      return this.statsStore[e]
    }, o.prototype.getCurrentWindowStats = function () {
      var e = this.frame,
        t = parseInt(e.titleBar.style.height, 10),
        r = e.frameBorderWidthDefault,
        o = e.frameBorderWidthFocused;
      return {
        left: e.getLeft(),
        top: e.getTop(),
        width: e.getWidth(),
        height: e.getHeight(),
        titleBarHeight: t,
        frameBorderWidthDefault: r,
        frameBorderWidthFocused: o,
        resizable: e.resizable,
        movable: e.movable
      }
    }, o.prototype.restoreWindow = function (t) {
      var r = this,
        o = r.frame,
        n = r.loadWindowStats(t.restoreMode),
        a = r.getCurrentWindowStats();
      o.frameBorderWidthDefault = n.frameBorderWidthDefault, o.frameBorderWidthFocused = n.frameBorderWidthFocused, o.htmlElement.style.borderWidth = o.frameBorderWidthFocused;
      var e = function () {
        t && 0 == t.restorePosition && (n.left = a.left, n.top = a.top), o.setPosition(n.left, n.top);
        o.setSize(n.width, n.height, !0), o.setResizable(n.resizable), o.setMovable(n.movable), r.clearWindowStats(t.restoreMode), r.keyListener && (window.removeEventListener("keydown", r.keyListener), o.iframe && o.iframe.contentWindow.removeEventListener("keydown", r.keyListener), r.keyListener = null), r.windowMode = "default", t && t.forceShowFrameComponents && o.showAllVisibleFrameComponents(), o.show();
        var e = "restored";
        t && t.eventType && (e = t.eventType), t && t.callback && t.callback(r.frame, {
          eventType: e
        }), r.eventListeners[e] && r.eventListeners[e](r.frame, {
          eventType: e
        })
      };
      t && t.animation ? r.animateFrame({
        duration: t.duration ? t.duration : r.animationDuration,
        frame: o,
        from: a,
        to: n,
        callback: e
      }) : e(), window.removeEventListener("resize", r.resizeListener)
    }, o.prototype.animateFrame = function (t) {
      var e = isNaN(t.fromAlpha) ? 1 : t.fromAlpha,
        r = t.from,
        o = t.to;
      (new n).set(t.frame).setDuration(t.duration ? t.duration : this.animationDuration).fromPosition(r.left, r.top, "LEFT_TOP").toPosition(o.left, o.top, "LEFT_TOP").fromWidth(r.width).fromHeight(r.height).toWidth(o.width).toHeight(o.height).fromAlpha(e).toAlpha(0 == t.toAlpha ? 0 : 1).start(0, !1).then(function (e) {
        t.callback()
      })
    }, o.prototype.setupDefaultEvents = function (r) {
      var e = this;
      e.maximizeButton && e.frame.on(e.maximizeButton, "click", function (e, t) {
        e.control.doMaximize({
          hideTitleBar: !0 === r.maximizeWithoutTitleBar,
          duration: 100,
          restoreKey: r.restoreKey ? r.restoreKey : "Escape",
          restoreDuration: 100,
          callback: function (e, t) {},
          restoreCallback: function (e, t) {
            jsFrame.showToast({
              text: e.getName() + " " + t.eventType
            })
          }
        })
      }), e.demaximizeButton && e.frame.on(e.demaximizeButton, "click", function (e, t) {
        e.control.doDemaximize({})
      }), e.minimizeButton && e.frame.on(e.minimizeButton, "click", function (e, t) {
        e.control.doMinimize({})
      }), e.deminimizeButton && e.frame.on(e.deminimizeButton, "click", function (e, t) {
        e.control.doDeminimize({})
      }), e.hideButton && e.frame.on(e.hideButton, "click", function (e, t) {
        e.control.doHide({
          align: "CENTER_BOTTOM"
        })
      })
    }, e.exports = o
  }, function (e, t) {
    var r = function () {
      function e() {
        var e = this;
        e._timerId = null, e._isRunning = !1, e._timerInterval = 0, e._internalCallback = function () {
          e._timerMethod && e._timerMethod(e);
          e._isRunning && (clearTimeout(e._timerId), e._timerId = setTimeout(e._internalCallback, e._timerInterval))
        }, e._timerMethod = null
      }
      return e.prototype.setCallback = function (e) {
        return this._timerMethod = e, this
      }, e.prototype.setIntervalMillis = function (e) {
        return this._timerInterval = e, this
      }, e.prototype.stopTimer = function () {
        return this._isRunning = !1, this
      }, e.prototype.startTimer = function () {
        var e = this;
        return 0 < e._timerInterval && (e._timerId = setTimeout(e._internalCallback, e._timerInterval), e._isRunning = !0), e
      }, e
    }();
    e.exports = r
  }, function (e, t, r) {
    r(11), e.exports.getStyle = function (C) {
      return C.showTitleBar = !0, C.showCloseButton = !0, C.titleBarCaptionFontSize = "11px", C.titleBarCaptionFontWeight = "normal", C.titleBarCaptionLeftMargin = null, C.titleBarCaptionColorDefault = "#4d494d", C.titleBarCaptionColorFocused = "#4d494d", C.titleBarHeight = "26px", C.titleBarColorDefault = "#f4f4f4", C.titleBarColorFocused = "#f4f4f4", C.titleBarBorderBottomDefault = "1px solid #b1aeb1", C.titleBarBorderBottomFocused = C.titleBarBorderBottomDefault, C.frameBorderRadius = "6px", C.frameBorderWidthDefault = "1px", C.frameBorderWidthFocused = "1px", C.frameBorderColorDefault = "#acacac", C.frameBorderColorFocused = "#acacac", C.frameBorderStyle = "solid", C.frameBoxShadow = "0px 0px 20px rgba(0, 0, 0, 0.3)", C.frameBackgroundColor = "transparent", C.frameComponents = new Array, C.getTitleBarStyle = function () {
        return C.focusedFrameOnly ? {
          titleBarClassNameDefault: "jsframe-preset-style-yosemite-focused",
          titleBarClassNameFocused: "jsframe-preset-style-yosemite-focused"
        } : {
          titleBarClassNameDefault: "jsframe-preset-style-yosemite-default",
          titleBarClassNameFocused: "jsframe-preset-style-yosemite-focused"
        }
      }, C.onInitialize = function () {
        var e = C.getPartsBuilder(),
          t = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAADxJREFUeNpiYEACyhKiU0AYWYyJARX4QDFOBRiAEWokTJc0lH4KpbcQNIEBzZEPQJgkN7Cg8begKwAIMAC1EQhm4CX95QAAAABJRU5ErkJggg==",
          r = "margin:0px;padding:0px;width:6px;height:6px",
          o = document.createElement("img");
        o.src = t, o.setAttribute("style", r);
        var n = document.createElement("img");
        n.src = t, n.setAttribute("style", r);
        var a = document.createElement("img");
        a.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAChJREFUeNpi/P//PwM+wMRAABBUwKIiKTYFSPvgkN9C0ARG2jsSIMAAWWAH8lrycVkAAAAASUVORK5CYII=", a.setAttribute("style", r);
        var i = document.createElement("img");
        i.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAFdJREFUeNpi/P//PwNeAFKgLCF6AIjnwMRAbJAYSI4FKnYHiJOBgjA1yUA8F24Ckq7/UDwHJsfEQACwwHSjGIuwLgXmBhWQ5N0Xr1OgGmBiDIyEvAkQYAB60iRIRtfWzQAAAABJRU5ErkJggg==", i.setAttribute("style", r);
        var l = document.createElement("img");
        l.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAABBJREFUeNpi+P//PwNAgAEACPwC/tuiTRYAAAAASUVORK5CYII=", l.setAttribute("style", "margin:0px;padding:0px;width:6px;height:6px");
        var s = e.buildTextButtonAppearance();
        s.size = 8, s.borderRadius = 4, s.borderWidth = 1, s.borderColorDefault = "#c6c6c6", s.borderColorFocused = "#fc615c", s.borderColorHovered = s.borderColorFocused, s.borderColorPressed = "#e64842", s.borderStyleDefault = "solid", s.borderStyleFocused = s.borderStyleDefault, s.borderStyleHovered = s.borderStyleDefault, s.borderStylePressed = s.borderStyleDefault, s.backgroundColorDefault = "#d0d0d0", s.backgroundColorFocused = "#fc615c", s.backgroundColorHovered = s.backgroundColorFocused, s.backgroundColorPressed = s.backgroundColorDefault, s.imageDefault = l, s.imageHovered = i, s.imagePressed = i, s.imageFocused = l;
        var d = e.buildButton(s),
          u = 8,
          p = -18,
          m = "LEFT_TOP";
        C.addFrameComponent("closeButton", d, u, p, m);
        var c = e.buildTextButtonAppearance();
        c.size = 8, c.borderRadius = 4, c.borderWidth = 1, c.borderColorDefault = "#c6c6c6", c.borderColorFocused = "#fdbe40", c.borderColorHovered = c.borderColorFocused, c.borderColorPressed = "#e1a12d", c.borderStyleDefault = "solid", c.borderStyleFocused = c.borderStyleDefault, c.borderStyleHovered = c.borderStyleDefault, c.borderStylePressed = c.borderStyleDefault, c.backgroundColorDefault = "#d0d0d0", c.backgroundColorFocused = "#fdbe40", c.backgroundColorHovered = c.backgroundColorFocused, c.backgroundColorPressed = c.backgroundColorDefault, c.imageDefault = l, c.imageHovered = a, c.imagePressed = a, c.imageFocused = l;
        var h = e.buildButton(c),
          f = e.buildButton(c);
        f.style.display = "none", u = 24, p = -18, m = "LEFT_TOP", C.addFrameComponent("minimizeButton", h, u, p, m), C.addFrameComponent("deminimizeButton", f, u, p, m);
        var y = e.buildTextButtonAppearance();
        y.size = 8, y.borderRadius = 4, y.borderWidth = 1, y.borderColorDefault = "#c6c6c6", y.borderColorFocused = "#34ca49", y.borderColorHovered = y.borderColorFocused, y.borderColorPressed = "#00af38", y.borderStyleDefault = "solid", y.borderStyleFocused = y.borderStyleDefault, y.borderStyleHovered = y.borderStyleDefault, y.borderStylePressed = y.borderStyleDefault, y.backgroundColorDefault = "#d0d0d0", y.backgroundColorFocused = "#34ca49", y.backgroundColorHovered = y.backgroundColorFocused, y.backgroundColorPressed = y.backgroundColorDefault, y.caption = null, y.imageDefault = l, y.imageHovered = o, y.imagePressed = o, y.imageFocused = l;
        var b = e.buildButton(y),
          g = e.buildButton(y);
        g.style.display = "none", u = 40, p = -18, m = "LEFT_TOP", C.addFrameComponent("zoomButton", b, u, p, m), C.addFrameComponent("dezoomButton", g, u, p, m)
      }, C
    }
  }, function (e, t, r) {
    var o = r(12);
    "string" == typeof o && (o = [
      [e.i, o, ""]
    ]);
    var n = {
      hmr: !0,
      transform: void 0,
      insertInto: void 0
    };
    r(1)(o, n);
    o.locals && (e.exports = o.locals)
  }, function (e, t, r) {
    (e.exports = r(0)(!1)).push([e.i, "/**\n * JSFrame Examples\n *\n * Copyright 2007-2017 Tom Misawa, riversun.org@gmail.com\n * Copyright 2007-2017 web2driver.com*/\n\n.jsframe-preset-style-yosemite-default {\n    background: -webkit-gradient(linear, left top, left bottom, color-stop(0.0, #f5f5f5, color-stop(1.0, #f8f7f2)));\n    background: -webkit-linear-gradient(top, #f5f5f5, #f8f7f2);\n    background: -moz-linear-gradient(top, #f5f5f5, #f8f7f2);\n    background: linear-gradient(top, #f5f5f5, #f8f7f2);\n    border-top-left-radius: 6px;\n    border-top-right-radius: 6px;\n}\n\n.jsframe-preset-style-yosemite-focused {\n    /* (c)2015 Johannes Jakob\n       Made with <3 in Germany */\n    background: -webkit-gradient(linear, left top, left bottom, color-stop(0.0, #ebebeb, color-stop(1.0, #d5d5d5)));\n    background: -webkit-linear-gradient(top, #ebebeb, #d5d5d5);\n    background: -moz-linear-gradient(top, #ebebeb, #d5d5d5);\n    background: linear-gradient(top, #ebebeb, #d5d5d5);\n    border-top-left-radius: 6px;\n    border-top-right-radius: 6px;\n}\n", ""])
  }, function (e, t, r) {
    r(14), e.exports.getStyle = function (h) {
      return h.showTitleBar = !0, h.showCloseButton = !0, h.titleBarCaptionFontSize = "12px", h.titleBarCaptionFontWeight = "normal", h.titleBarCaptionLeftMargin = "10px", h.titleBarCaptionColorDefault = "#9b9a9b", h.titleBarCaptionColorFocused = "#4d494d", h.titleBarHeight = "30px", h.titleBarColorDefault = "white", h.titleBarColorFocused = "white", h.titleBarBorderBottomDefault = "solid 1px #aaaaaa", h.titleBarBorderBottomFocused = "solid 1px #1883d7", h.frameBorderRadius = "0px", h.frameBorderWidthDefault = "1px", h.frameBorderWidthFocused = "1px", h.frameBorderColorDefault = "#aaaaaa", h.frameBorderColorFocused = "#1883d7", h.frameBorderStyle = "solid", h.frameBoxShadow = null, h.frameBackgroundColor = "transparent", h.frameComponents = new Array, h.frameHeightAdjust = 0, h.getTitleBarStyle = function () {
        return h.focusedFrameOnly ? {
          titleBarClassNameDefault: "jsframe-preset-style-redstone-focused",
          titleBarClassNameFocused: "jsframe-preset-style-redstone-focused"
        } : {
          titleBarClassNameDefault: "jsframe-preset-style-redstone-default",
          titleBarClassNameFocused: "jsframe-preset-style-redstone-focused"
        }
      }, h.onInitialize = function () {
        var e = h.getPartsBuilder(),
          t = e.buildTextButtonAppearance();
        t.width = 45, t.height = 28, t.borderRadius = 0, t.borderWidth = 0, t.borderColorDefault = "#c6c6c6", t.borderColorFocused = "#fc615c", t.borderColorHovered = t.borderColorFocused, t.borderColorPressed = "#e64842", t.borderStyleDefault = "solid", t.borderStyleFocused = t.borderStyleDefault, t.borderStyleHovered = t.borderStyleDefault, t.borderStylePressed = t.borderStyleDefault, t.backgroundColorDefault = "white", t.backgroundColorFocused = "white", t.backgroundColorHovered = "#e81123", t.backgroundColorPressed = "#f1707a", t.caption = "╳", t.captionColorDefault = "#9b9a9b", t.captionColorFocused = "black", t.captionColorHovered = "white", t.captionColorPressed = "white", t.captionShiftYpx = 1, t.captionFontRatio = .6;
        var r = e.buildTextButton(t),
          o = 0,
          n = -parseInt(h.titleBarHeight),
          a = "RIGHT_TOP";
        h.addFrameComponent("closeButton", r, o, n, a);
        var i = e.buildTextButtonAppearance();
        i.width = 45, i.height = 28, i.borderRadius = 0, i.borderWidth = 0, i.borderColorDefault = "#c6c6c6", i.borderColorFocused = "#fc615c", i.borderColorHovered = i.borderColorFocused, i.borderColorPressed = "#e64842", i.borderStyleDefault = "solid", i.borderStyleFocused = i.borderStyleDefault, i.borderStyleHovered = i.borderStyleDefault, i.borderStylePressed = i.borderStyleDefault, i.backgroundColorDefault = "white", i.backgroundColorFocused = "white", i.backgroundColorHovered = "#e5e5e5", i.backgroundColorPressed = "#cccccc", i.caption = "☐", i.captionColorDefault = "#9b9a9b", i.captionColorFocused = "black", i.captionColorHovered = "black", i.captionColorPressed = "black", i.captionShiftYpx = 1, i.captionFontRatio = .55;
        var l = e.buildTextButton(i);
        o = -46, n = -parseInt(h.titleBarHeight), a = "RIGHT_TOP", h.addFrameComponent("maximizeButton", l, o, n, a);
        var s = e.buildTextButtonAppearance();
        s.width = 45, s.height = 28, s.borderRadius = 0, s.borderWidth = 0, s.borderColorDefault = "#c6c6c6", s.borderColorFocused = "#fc615c", s.borderColorHovered = s.borderColorFocused, s.borderColorPressed = "#e64842", s.borderStyleDefault = "solid", s.borderStyleFocused = s.borderStyleDefault, s.borderStyleHovered = s.borderStyleDefault, s.borderStylePressed = s.borderStyleDefault, s.backgroundColorDefault = "white", s.backgroundColorFocused = "white", s.backgroundColorHovered = "#e5e5e5", s.backgroundColorPressed = "#cccccc", s.caption = "＿", s.captionColorDefault = "#9b9a9b", s.captionColorFocused = "black", s.captionColorHovered = "black", s.captionColorPressed = "black", s.captionShiftYpx = -2, s.captionFontRatio = .3;
        var d = e.buildTextButton(s);
        o = -92, n = -parseInt(h.titleBarHeight), a = "RIGHT_TOP", h.addFrameComponent("minimizeButton", d, o, n, a);
        var u = e.buildTextButtonAppearance();
        u.width = 45, u.height = 28, u.borderRadius = 0, u.borderWidth = 0, u.borderColorDefault = "#c6c6c6", u.borderColorFocused = "#fc615c", u.borderColorHovered = u.borderColorFocused, u.borderColorPressed = "#e64842", u.borderStyleDefault = "solid", u.borderStyleFocused = u.borderStyleDefault, u.borderStyleHovered = u.borderStyleDefault, u.borderStylePressed = u.borderStyleDefault, u.backgroundColorDefault = "white", u.backgroundColorFocused = "white", u.backgroundColorHovered = "#e5e5e5", u.backgroundColorPressed = "#cccccc", u.caption = "▣", u.captionColorDefault = "#9b9a9b", u.captionColorFocused = "black", u.captionColorHovered = "black", u.captionColorPressed = "black", u.captionShiftYpx = 1, u.captionFontRatio = .6;
        var p = e.buildTextButton(u);
        o = -92, n = -parseInt(h.titleBarHeight), a = "RIGHT_TOP", p.style.display = "none", h.addFrameComponent("deminimizeButton", p, o, n, a);
        var m = e.buildTextButtonAppearance();
        m.width = 45, m.height = 28, m.borderRadius = 0, m.borderWidth = 0, m.borderColorDefault = "#c6c6c6", m.borderColorFocused = "#fc615c", m.borderColorHovered = m.borderColorFocused, m.borderColorPressed = "#e64842", m.borderStyleDefault = "solid", m.borderStyleFocused = m.borderStyleDefault, m.borderStyleHovered = m.borderStyleDefault, m.borderStylePressed = m.borderStyleDefault, m.backgroundColorDefault = "white", m.backgroundColorFocused = "white", m.backgroundColorHovered = "#e5e5e5", m.backgroundColorPressed = "#cccccc", m.caption = "❏", m.captionColorDefault = "#9b9a9b", m.captionColorFocused = "black", m.captionColorHovered = "black", m.captionColorPressed = "black", m.captionShiftYpx = 1, m.captionFontRatio = .55;
        var c = e.buildTextButton(m);
        o = -46, n = -parseInt(h.titleBarHeight), a = "RIGHT_TOP", c.style.display = "none", h.addFrameComponent("restoreButton", c, o, n, a)
      }, h
    }
  }, function (e, t, r) {
    var o = r(15);
    "string" == typeof o && (o = [
      [e.i, o, ""]
    ]);
    var n = {
      hmr: !0,
      transform: void 0,
      insertInto: void 0
    };
    r(1)(o, n);
    o.locals && (e.exports = o.locals)
  }, function (e, t, r) {
    (e.exports = r(0)(!1)).push([e.i, ".jsframe-preset-style-redstone-default {\n    background: white;\n    border-top-left-radius: 0px;\n    border-top-right-radius: 0px;\n}\n\n.jsframe-preset-style-redstone-focused {\n    background: white;\n    border-top-left-radius: 0px;\n    border-top-right-radius: 0px;\n}\n", ""])
  }, function (e, t, r) {
    r(17), e.exports.getStyle = function (n) {
      return n.showTitleBar = !1, n.showCloseButton = !0, n.titleBarCaptionFontSize = "12px", n.titleBarCaptionFontWeight = "normal", n.titleBarCaptionLeftMargin = "10px", n.titleBarCaptionColorDefault = "#4d494d", n.titleBarCaptionColorFocused = "#4d494d", n.titleBarHeight = "5px", n.titleBarColorDefault = "white", n.titleBarColorFocused = "white", n.titleBarBorderBottomDefault = null, n.titleBarBorderBottomFocused = null, n.frameBorderRadius = "6px", n.frameBorderWidthDefault = "1px", n.frameBorderWidthFocused = "1px", n.frameBorderColorDefault = "#aaaaaa", n.frameBorderColorFocused = "#aaaaaa", n.frameBorderStyle = "solid", n.frameBoxShadow = "2px 2px 5px  rgba(0, 0, 0, 0.5)", n.frameBackgroundColor = "white", n.frameComponents = new Array, n.frameHeightAdjust = 2, n.getTitleBarStyle = function () {
        return n.focusedFrameOnly ? {
          titleBarClassNameDefault: "jsframe-preset-style-popup-focused",
          titleBarClassNameFocused: "jsframe-preset-style-popup-focused"
        } : {
          titleBarClassNameDefault: "jsframe-preset-style-popup-default",
          titleBarClassNameFocused: "jsframe-preset-style-popup-focused"
        }
      }, n.onInitialize = function () {
        var e = n.getPartsBuilder(),
          t = e.buildTextButtonAppearance();
        t.width = 20, t.height = 20, t.borderRadius = 10, t.borderWidth = 1, t.borderColorDefault = "#cccccc", t.borderColorFocused = "#cccccc", t.borderColorHovered = "#dddddd", t.borderColorPressed = "#eeeeee", t.borderStyleDefault = "solid", t.borderStyleFocused = t.borderStyleDefault, t.borderStyleHovered = t.borderStyleDefault, t.borderStylePressed = t.borderStyleDefault, t.backgroundColorDefault = "white", t.backgroundColorFocused = "white", t.backgroundColorHovered = "#eeeeee", t.backgroundColorPressed = "#dddddd", t.backgroundBoxShadow = "2px 2px 5px  rgba(0, 0, 0, 0.5)", t.caption = "✖", t.captionColorDefault = "black", t.captionColorFocused = "black", t.captionColorHovered = "white", t.captionColorPressed = "white", t.captionShiftYpx = 1, t.captionFontRatio = .6;
        var r = e.buildTextButton(t),
          o = -6 - parseInt(n.titleBarHeight);
        n.addFrameComponent("closeButton", r, 10, o, "RIGHT_TOP")
      }, n
    }
  }, function (e, t, r) {
    var o = r(18);
    "string" == typeof o && (o = [
      [e.i, o, ""]
    ]);
    var n = {
      hmr: !0,
      transform: void 0,
      insertInto: void 0
    };
    r(1)(o, n);
    o.locals && (e.exports = o.locals)
  }, function (e, t, r) {
    (e.exports = r(0)(!1)).push([e.i, ".jsframe-preset-style-popup-default {\n    background: white;\n    border-top-left-radius: 0px;\n    border-top-right-radius: 0px;\n}\n\n.jsframe-preset-style-popup-focused {\n    background: white;\n    border-top-left-radius: 0px;\n    border-top-right-radius: 0px;\n}\n", ""])
  }, function (e, t) {
    e.exports.getStyle = function (o) {
      return o.showTitleBar = !1, o.showCloseButton = !0, o.titleBarCaptionFontSize = "0px", o.titleBarCaptionFontWeight = "normal", o.titleBarCaptionLeftMargin = "0px", o.titleBarCaptionColorDefault = "transparent", o.titleBarCaptionColorFocused = "transparent", o.titleBarHeight = "0px", o.titleBarColorDefault = "transparent", o.titleBarColorFocused = "transparent", o.titleBarBorderBottomDefault = null, o.titleBarBorderBottomFocused = null, o.frameBorderRadius = "10px", o.frameBorderWidthDefault = "0px", o.frameBorderWidthFocused = "0px", o.frameBorderColorDefault = "transparent", o.frameBorderColorFocused = "transparent", o.frameBorderStyle = "solid", o.frameBoxShadow = "2px 2px 15px  rgba(0, 0, 0, 0.5)", o.frameBackgroundColor = "transparent", o.frameComponents = [], o.frameHeightAdjust = 2, o.captionClor = "darkgray", o.pullUpDisabled = !1, o.getTitleBarStyle = function () {
        return o.focusedFrameOnly, {
          titleBarClassNameDefault: " ",
          titleBarClassNameFocused: " "
        }
      }, o.onInitialize = function () {
        var e = o.getPartsBuilder(),
          t = e.buildTextButtonAppearance();
        t.width = 20, t.height = 20, t.borderRadius = 10, t.borderWidth = 0, t.borderColorDefault = "#cccccc", t.borderColorFocused = "#cccccc", t.borderColorHovered = "#dddddd", t.borderColorPressed = "#eeeeee", t.borderStyleDefault = "solid", t.borderStyleFocused = t.borderStyleDefault, t.borderStyleHovered = t.borderStyleDefault, t.borderStylePressed = t.borderStyleDefault, t.backgroundColorDefault = "transparent", t.backgroundColorFocused = "transparent", t.backgroundColorHovered = "transparent", t.backgroundColorPressed = "transparent", t.backgroundBoxShadow = null, t.caption = "✖", t.captionColorDefault = o.captionClor, t.captionColorFocused = o.captionClor, t.captionColorHovered = o.captionClor, t.captionColorPressed = o.captionClor, t.captionShiftYpx = 1, t.captionFontRatio = .6;
        var r = e.buildTextButton(t);
        o.addFrameComponent("closeButton", r, -6, 3, "RIGHT_TOP")
      }, o
    }
  }, function (e, t, r) {
    r(21);
    var n = r(23);

    function a(e, t, r) {
      var o, n = e.getPartsBuilder(),
        a = 0;
      for (var i in o = r ? t.titleBar.buttonsOnLeft : t.titleBar.buttons) {
        var l = o[i],
          s = n.buildTextButtonAppearance();
        s.fa = l.fa, s.width = t.titleBar.buttonWidth, s.height = t.titleBar.buttonHeight, s.borderRadius = 0, s.borderWidth = 0, s.borderColorDefault = "#c6c6c6", s.borderColorFocused = "#fc615c", s.borderColorHovered = s.borderColorFocused, s.borderColorPressed = "#e64842", s.borderStyleDefault = "solid", s.borderStyleFocused = s.borderStyleDefault, s.borderStyleHovered = s.borderStyleDefault, s.borderStylePressed = s.borderStyleDefault, s.backgroundColorDefault = "transparent", s.backgroundColorFocused = "transparent", s.backgroundColorHovered = "transparent", s.backgroundColorPressed = "transparent";
        var d = y(t.titleBar.buttonColor);
        s.captionColorDefault = t.titleBar.buttonColor, s.captionColorFocused = t.titleBar.buttonColor, s.captionColorHovered = d.hovered, s.captionColorPressed = d.pressed, s.captionShiftYpx = 0, s.captionFontRatio = 1;
        var u = n.buildTextButton(s);
        l.visible ? u.style.display = "flex" : (r ? a -= t.titleBar.buttonWidth : a += t.titleBar.buttonWidth, u.style.display = "none");
        var p, m, c = parseInt(e.titleBarHeight),
          h = a,
          f = -c + (c - s.height) / 2;
        p = r ? "LEFT_TOP" : "RIGHT_TOP", l.childMenuHTML && ((m = document.createElement("div")).id = l.name + "_child_menu", m.innerHTML = l.childMenuHTML, m.style.position = "absolute", m.style.width = (l.childMenuWidth ? l.childMenuWidth : 200) + "px", m.style.top = parseInt(u.style.top, 10) + parseInt(u.style.height, 10) / 2 + c / 2 + "px", m.style.left = u.style.left, m.style.display = "none", u.appendChild(m)), e.addFrameComponent(l.name, u, h, f, p, {
          childMenu: m
        }), a += r ? t.titleBar.buttonWidth : -t.titleBar.buttonWidth
      }
    }

    function y(e) {
      var t = document.createElement("canvas");
      t.height = 1, t.width = 1;
      var r = t.getContext("2d");
      r.fillStyle = e, r.fillRect(0, 0, 1, 1);
      var o = r.getImageData(0, 0, 1, 1).data,
        n = o[0],
        a = o[1],
        i = o[2],
        l = o[3] / 255,
        s = .85 * l;
      return {
        src: "rgb(" + n + "," + a + "," + i + "," + s + ")",
        hovered: "rgb(" + n + "," + a + "," + i + "," + s + ")",
        pressed: "rgb(" + n + "," + a + "," + i + "," + .75 * l + ")"
      }
    }
    e.exports.getStyle = function (e, t) {
      var r = {
          border: {
            color: "transparent",
            width: 0,
            radius: 6
          },
          control: {
            maximizeWithoutTitleBar: !1,
            restoreKey: "Escape"
          },
          titleBar: {
            color: "white",
            background: "black",
            leftMargin: 20,
            height: 30,
            fontSize: 12,
            buttonWidth: 36,
            buttonHeight: 16,
            buttonColor: "white",
            buttons: [{
              fa: "fas fa-times",
              name: "closeButton",
              visible: !0
            }, {
              fa: "far fa-window-maximize",
              name: "maximizeButton",
              visible: !0
            }, {
              fa: "far fa-window-restore",
              name: "restoreButton",
              visible: !1
            }, {
              fa: "far fa-window-minimize",
              name: "minimizeButton",
              visible: !0
            }, {
              fa: "fas fa-caret-up",
              name: "deminimizeButton",
              visible: !1
            }],
            buttonsOnLeft: []
          }
        },
        o = r;
      return t && n.objectAssign(r, t), e.showTitleBar = !0, e.showCloseButton = !0, e.titleBarCaptionFontSize = o.titleBar.fontSize + "px", e.titleBarCaptionFontWeight = "normal", e.titleBarCaptionLeftMargin = o.titleBar.leftMargin + "px", e.titleBarCaptionColorDefault = o.titleBar.color, e.titleBarCaptionColorFocused = o.titleBar.color, e.titleBarCaptionTextShadow = null, e.titleBarCaptionTextAlign = "left", e.titleBarHeight = o.titleBar.height + "px", e.titleBarColorDefault = o.titleBar.background, e.titleBarColorFocused = o.titleBar.background, e.titleBarBorderBottomDefault = "solid 0px #aaaaaa", e.titleBarBorderBottomFocused = "solid 0px #1883d7", e.frameBorderRadius = o.border.radius + "px", e.frameBorderWidthDefault = o.border.width + "px", e.frameBorderWidthFocused = o.border.width + "px", e.frameBorderColorDefault = o.border.color, e.frameBorderColorFocused = o.border.color, e.frameBorderStyle = "solid", e.frameBoxShadow = o.border.shadow, e.frameBackgroundColor = "transparent", e.frameComponents = new Array, e.frameHeightAdjust = 0, e.getTitleBarStyle = function () {
        return e.focusedFrameOnly, {
          titleBarClassNameDefault: " ",
          titleBarClassNameFocused: " "
        }
      }, e.onInitialize = function () {
        a(e, o, !1), a(e, o, !0)
      }, e
    }
  }, function (e, t, r) {
    var o = r(22);
    "string" == typeof o && (o = [
      [e.i, o, ""]
    ]);
    var n = {
      hmr: !0,
      transform: void 0,
      insertInto: void 0
    };
    r(1)(o, n);
    o.locals && (e.exports = o.locals)
  }, function (e, t, r) {
    (e.exports = r(0)(!1)).push([e.i, ".jsframe-preset-style-material-default {\n    background: black;\n    border-top-left-radius: 6px;\n    border-top-right-radius: 6px;\n}\n\n.jsframe-preset-style-material-focused {\n    background: black;\n    border-top-left-radius: 36px;\n    border-top-right-radius: 36px;\n}\n", ""])
  }, function (e, t) {
    var r = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (e) {
      return typeof e
    } : function (e) {
      return e && "function" == typeof Symbol && e.constructor === Symbol && e !== Symbol.prototype ? "symbol" : typeof e
    };

    function l(e, t, r) {
      return t in e ? Object.defineProperty(e, t, {
        value: r,
        enumerable: !0,
        configurable: !0,
        writable: !0
      }) : e[t] = r, e
    }

    function s(e) {
      return e && "object" === (void 0 === e ? "undefined" : r(e)) && !Array.isArray(e)
    }
    e.exports.objectAssign = function e(t) {
      for (var r = arguments.length, o = Array(1 < r ? r - 1 : 0), n = 1; n < r; n++) o[n - 1] = arguments[n];
      if (!o.length) return t;
      var a = o.shift();
      if (s(t) && s(a))
        for (var i in a) s(a[i]) ? (t[i] || Object.assign(t, l({}, i, {})), e(t[i], a[i])) : Object.assign(t, l({}, i, a[i]));
      return e.apply(void 0, [t].concat(o))
    }
  }])
});